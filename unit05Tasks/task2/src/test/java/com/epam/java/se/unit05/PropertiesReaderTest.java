package com.epam.java.se.unit05;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 13.03.2017.
 */
public class PropertiesReaderTest {

    @Test
    public void loadPropertiesFromExistingPropFile() throws Exception {
        PropertiesReader reader = new PropertiesReader("exists.properties");
        reader.loadPropertiesFile();
        assertFalse(reader.getProperties() == null); //questionably
    }

    @Test(expected = FileNotFoundException.class)
    public void loadPropertiesFromNotExistingPropFile() throws Exception {
        PropertiesReader reader = new PropertiesReader("notExists.properties");
        reader.loadPropertiesFile();
    }

    @Test
    public void getElementWithExistingKey() throws Exception {
        PropertiesReader reader = new PropertiesReader("exists.properties");
        reader.loadPropertiesFile();

        String element = reader.getElementByKey("existingKey");
        assertTrue(element.equals("elementGotByKey"));
    }

    @Test(expected = KeyNotFoundException.class)
    public void getElementWithNotExistingKey() throws Exception {
        PropertiesReader reader = new PropertiesReader("exists.properties");
        reader.loadPropertiesFile();

        String element = reader.getElementByKey("existingKey");
        assertTrue(element.equals("elementGotByKey"));
    }

    //localeTest?
}