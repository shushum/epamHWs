package com.epam.java.se.unit06Task;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Yegor on 22.03.2017.
 */
public class PropertiesReader {
    private String pathToPropertiesFile;
    /**
     * Storage for read propertiesMap from .propertiesMap file.
     */
    private Map<String, String > propertiesMap;

    /**
     * Creates a PropertiesReader binded to specified .propertiesMap file.
     * @param pathToPropertiesFile String path to .propertiesMap file.
     */
    public PropertiesReader(String pathToPropertiesFile) {
        this.pathToPropertiesFile = pathToPropertiesFile;
        this.propertiesMap = new HashMap<>();
    }

    /**
     * Allows to change bindings to another .propertiesMap file.
     * @param newPathToPropertiesFile String path to another .propertiesMap file.
     * @return new PropertiesReader binded to another .propertiesMap file.
     */
    public PropertiesReader changePathToPropertiesFile(String newPathToPropertiesFile) {
        return new PropertiesReader(newPathToPropertiesFile);
    }

    public Map<String, String> getPropertiesMap() {
        return propertiesMap;
    }

    public void getPropertiesAsHashMap() throws FileNotFoundException{
        convertToHashMapProperties(loadedFromFile());
    }

    /**
     * Loads {@code Properties} from .propertiesMap file to PropertiesReader's 'propertiesMap' field.
     * @throws FileNotFoundException if required .propertiesMap file does not exist.
     */
    private Properties loadedFromFile() throws FileNotFoundException{
        Properties propertiesBuffer = new Properties();

        try (BufferedReader reader = new BufferedReader(new FileReader(pathToPropertiesFile))){
            propertiesBuffer.load(reader);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return propertiesBuffer;
    }

    private void convertToHashMapProperties(Properties properties) {
        properties.stringPropertyNames()
                .forEach(key -> propertiesMap.put(key, properties.getProperty(key)));
    }
}
