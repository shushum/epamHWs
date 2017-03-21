package com.epam.java.se.unit06Task;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 22.03.2017.
 */
public class PropertiesReaderTest {
    @Test
    public void getPropertiesAsHashMapFromNotEmptyFile() throws Exception {
        PropertiesReader propReader = new PropertiesReader("resourcesForTest.properties");

        propReader.getPropertiesAsHashMap();

        assertFalse(propReader.getPropertiesMap().isEmpty());
    }

    @Test
    public void getPropertiesAsHashMapFromEmptyFile() throws Exception {
        PropertiesReader propReader = new PropertiesReader("resourcesEmpty.properties");

        propReader.getPropertiesAsHashMap();

        assertTrue(propReader.getPropertiesMap().isEmpty());
    }

    @Test(expected = FileNotFoundException.class)
    public void getPropertiesAsHashMapFromWrongFile() throws Exception {
        PropertiesReader propReader = new PropertiesReader("wrongFile");

        propReader.getPropertiesAsHashMap();
    }

    @Test
    public void answerToQuestion() throws Exception {
        Map<String, String> mapToInsertAlreadyPresentedKey = new HashMap<>();

        mapToInsertAlreadyPresentedKey.put("someKey", "This value should change into 'rabbit'!");
        mapToInsertAlreadyPresentedKey.put("someKey", "rabbit");

        assertTrue(mapToInsertAlreadyPresentedKey.get("someKey").equals("rabbit"));
        assertTrue(mapToInsertAlreadyPresentedKey.containsValue("rabbit"));
        assertFalse(mapToInsertAlreadyPresentedKey.containsValue("This value should change into 'rabbit'!"));
        //the previous value would be replaced with new value
    }
}