package com.epam.java.se.unit05;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 13.03.2017.
 */
public class PropertiesReaderTest {

    @Test
    public void loadPropertiesFromExistingNotEmptyPropFile() throws Exception {
        PropertiesReader reader =
                new PropertiesReader("resourcesForTest.properties");
        reader.loadPropertiesFile();
        assertTrue(reader.getProperties().size() == 2);
    }

    @Test
    public void loadPropertiesFromExistingEmptyPropFile() throws Exception {
        PropertiesReader reader =
                new PropertiesReader("resourcesEmpty.properties");
        reader.loadPropertiesFile();
        assertTrue(reader.getProperties().isEmpty());
    }

    @Test(expected = FileNotFoundException.class)
    public void loadPropertiesFromNotExistingPropFile() throws Exception {
        PropertiesReader reader = new PropertiesReader("notExists.properties");
        reader.loadPropertiesFile();
    }

    @Test
    public void getElementWithExistingKey() throws Exception {
        PropertiesReader reader = new PropertiesReader("resourcesForTest.properties");
        reader.loadPropertiesFile();

        assertTrue(reader.getElementByKey("1").equals("element for key '1'"));
    }

    @Test(expected = KeyNotFoundException.class)
    public void getElementWithNotExistingKey() throws Exception {
        PropertiesReader reader = new PropertiesReader("resourcesForTest.properties");
        reader.loadPropertiesFile();

        reader.getElementByKey("2");
    }

    @Test(expected = PropertiesFileIsEmptyException.class)
    public void getElementFromEmptyPropFile() throws Exception {
        PropertiesReader reader = new PropertiesReader("resourcesEmpty.properties");
        reader.loadPropertiesFile();

        reader.getElementByKey("2");
    }

    @Test
    public void localeTestForUniversalAssurance() throws Exception {
        PropertiesReader reader = new PropertiesReader("resourcesRus.properties");
        reader.loadPropertiesFile();

        assertTrue(reader.getElementByKey("1").equals("значение для ключа '1'"));
    }
}