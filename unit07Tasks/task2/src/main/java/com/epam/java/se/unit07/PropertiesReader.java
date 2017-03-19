package com.epam.java.se.unit07;

/**
 * Created by Yegor on 13.03.2017.
 */

import java.io.*;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * A class designed to read any .properties file and to get element from it by key.
 * <p>
 * Each PropertiesReader are bind to the specific .properties file. This is necessary to avoid key duplicating from
 * different .properties files.
 */
public class PropertiesReader implements Callable<Properties> {
    private final File propertiesFile;
    /**
     * Storage for read properties from .properties file.
     */
    private Properties properties;
    private boolean run;

    /**
     * Creates a PropertiesReader binded to specified .properties file.
     *
     * @param propertiesFile .properties file.
     */
    public PropertiesReader(File propertiesFile) {

        this.propertiesFile = propertiesFile;
        this.properties = new Properties();
        run = true;
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

    public Properties call() {

        while (run) {
            try {
                loadPropertiesFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (OverlappingFileLockException e) {
                timeOutBeforeAnotherLoadAttempt();
            }
        }

        return properties;
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
            stopLoadAttempts();

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void timeOutBeforeAnotherLoadAttempt() {
        System.out.println(propertiesFile + " is currently read by another Properties Reader.");

        try {
            TimeUnit.MICROSECONDS.sleep(100);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println(this.toString() + "making another load attempt.");
    }

    private void stopLoadAttempts() {
        run = false;
    }
}