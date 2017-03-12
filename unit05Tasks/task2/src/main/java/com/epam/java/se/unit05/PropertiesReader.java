package com.epam.java.se.unit05;

import java.util.Properties;

/**
 * Created by Yegor on 13.03.2017.
 */
public class PropertiesReader {
    private String pathToPropetrtiesFile;
    private Properties properties;

    public PropertiesReader(String pathToPropertiesFile){
        this.pathToPropetrtiesFile = pathToPropertiesFile;
        this.properties = new Properties();
    }

    public PropertiesReader changePathToPropertiesFile(String newPathToPropertiesFile){
        return new PropertiesReader(newPathToPropertiesFile);
    }

    public void loadPropertiesFile(){

    }

    public String getElementByKey(String key){
        return "1";
    }

    public Properties getProperties() {
        return properties;
    }
}
