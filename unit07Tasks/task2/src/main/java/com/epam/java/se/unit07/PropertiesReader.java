package com.epam.java.se.unit07;

/**
 * Created by Yegor on 13.03.2017.
 */

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * A class designed to read any .properties file and to get element from it by key.
 * <p>
 * Each PropertiesReader are bind to the specific .properties file. This is necessary to avoid key duplicating from
 * different .properties files.
 */
public class PropertiesReader extends Thread {
    private final File propertiesFile;
    /**
     * Storage for read properties from .properties file.
     */
    private Properties properties;

    /**
     * Creates a PropertiesReader binded to specified .properties file.
     *
     * @param propertiesFile .properties file.
     */
    public PropertiesReader(File propertiesFile) {

        this.propertiesFile = propertiesFile;
        this.properties = new Properties();
    }

    /**
     * Allows to change bindings to another .properties file.
     *
     * @param newPropertiesFile String path to another .properties file.
     * @return new PropertiesReader binded to another .properties file.
     */
    public PropertiesReader changePathToPropertiesFile(File newPropertiesFile) {
        return new PropertiesReader(newPropertiesFile);
    }

    public void run() {
        try {

            loadPropertiesFile();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, String> getElementsByKeys(List<String> keys) {
        HashMap<String, String> keysWithElements = new HashMap<>();

        try {
            for (String key : keys) {
                try {
                    keysWithElements.put(key, getElementByKey(key));
                } catch (KeyNotFoundException e) {
                    keysWithElements.put(key, "Not found in " + propertiesFile);
                }
            }
        } catch (PropertiesFileIsEmptyException e) {
            keysWithElements.put("Exception", "Empty .properties file");
        }
        return keysWithElements;
    }

    /**
     * Loads {@code Properties} from .properties file to PropertiesReader's 'properties' field.
     *
     * @throws FileNotFoundException if required .properties file does not exist.
     */
    private void loadPropertiesFile() throws FileNotFoundException {

        try (FileInputStream inputStream = new FileInputStream(propertiesFile);
             BufferedInputStream reader = new BufferedInputStream(inputStream)) {

            FileLock lock = inputStream.getChannel().tryLock(0L, Long.MAX_VALUE, true);

            properties.load(reader);

            lock.release();


        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets element from .properties file defined by key.
     *
     * @param key key of required element.
     * @return String element of the key.
     * @throws PropertiesFileIsEmptyException if loaded .properties file is empty.
     * @throws KeyNotFoundException           if such key does not exist in loaded .properties file.
     */
    private String getElementByKey(String key) throws PropertiesFileIsEmptyException, KeyNotFoundException {

        if (properties.isEmpty()) {
            throw new PropertiesFileIsEmptyException("Properties file is empty.");
        }

        if (properties.getProperty(key) == null) {
            throw new KeyNotFoundException("Such key does not exist.");
        }

        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return properties;
    }
}