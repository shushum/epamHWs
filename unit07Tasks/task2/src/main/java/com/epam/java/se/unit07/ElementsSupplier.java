package com.epam.java.se.unit07;

import java.util.Properties;

/**
 * Created by Yegor on 20.03.2017.
 */
public class ElementsSupplier {
    private Properties properties;

    public ElementsSupplier(Properties properties){
        this.properties = properties;
    }

    /**
     * Gets element from .properties file defined by key.
     *
     * @param key key of required element.
     * @return String element of the key.
     * @throws PropertiesFileIsEmptyException if loaded .properties file is empty.
     * @throws KeyNotFoundException           if such key does not exist in loaded .properties file.
     */
    public String getElementByKey(String key) throws PropertiesFileIsEmptyException, KeyNotFoundException {

        if (properties.isEmpty()) {
            throw new PropertiesFileIsEmptyException("Properties file is empty.");
        }

        if (properties.getProperty(key) == null) {
            throw new KeyNotFoundException("Such key does not exist.");
        }

        return properties.getProperty(key);
    }
}
