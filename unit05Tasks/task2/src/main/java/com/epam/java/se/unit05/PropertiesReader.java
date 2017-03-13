package com.epam.java.se.unit05;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Yegor on 13.03.2017.
 */

/**
 * A class designed to read any .properties file and to get element from it by key.
 *
 * Each PropertiesReader are bind to the specific .properties file. This is necessary to avoid key duplicating from
 * different .properties files.
 */
public class PropertiesReader {
    private String pathToPropertiesFile;
    /**
     * Storage for read properties from .properties file.
     */
    private Properties properties;

    /**
     * Creates a PropertiesReader binded to specified .properties file.
     * @param pathToPropertiesFile String path to .properties file.
     */
    public PropertiesReader(String pathToPropertiesFile) {
        this.pathToPropertiesFile = pathToPropertiesFile;
        this.properties = new Properties();
    }

    /**
     * Allows to change bindings to another .properties file.
     * @param newPathToPropertiesFile String path to another .properties file.
     * @return new PropertiesReader binded to another .properties file.
     */
    public PropertiesReader changePathToPropertiesFile(String newPathToPropertiesFile) {
        return new PropertiesReader(newPathToPropertiesFile);
    }

    /**
     * Loads {@code Properties} from .properties file to PropertiesReader's 'properties' field.
     * @throws FileNotFoundException if required .properties file does not exist.
     */
    public void loadPropertiesFile() throws FileNotFoundException{
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToPropertiesFile))){
            properties.load(reader);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets element from .properties file defined by key.
     * @param key key of required element.
     * @return String element of the key.
     * @throws PropertiesFileIsEmptyException if loaded .properties file is empty.
     * @throws KeyNotFoundException if such key does not exist in loaded .properties file.
     */
    public String getElementByKey(String key) throws PropertiesFileIsEmptyException, KeyNotFoundException {

        if (properties.isEmpty()){
            throw new PropertiesFileIsEmptyException("Properties file is empty.");
        }

        if (properties.getProperty(key) == null){
            throw new KeyNotFoundException("Such a key does not exist.");
        }

        return properties.getProperty(key);
    }
    
    public Properties getProperties() {
        return properties;
    }
}
