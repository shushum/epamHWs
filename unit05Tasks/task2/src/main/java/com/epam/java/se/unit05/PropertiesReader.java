package com.epam.java.se.unit05;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Yegor on 13.03.2017.
 */
public class PropertiesReader {
    private String pathToPropertiesFile;
    private Properties properties;

    public PropertiesReader(String pathToPropertiesFile) {
        this.pathToPropertiesFile = pathToPropertiesFile;
        this.properties = new Properties();
    }

    public PropertiesReader changePathToPropertiesFile(String newPathToPropertiesFile) {
        return new PropertiesReader(newPathToPropertiesFile);
    }

    public void loadPropertiesFile() throws FileNotFoundException{
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToPropertiesFile))){
            properties.load(reader);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
