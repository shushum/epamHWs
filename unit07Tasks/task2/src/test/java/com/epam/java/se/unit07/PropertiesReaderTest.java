package com.epam.java.se.unit07;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.channels.OverlappingFileLockException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 19.03.2017.
 */
public class PropertiesReaderTest {

    @Test
    public void multipleThreadsReadFromOneFile() throws Exception {
        File properties = new File("resourcesForTest.properties");

        PropertiesReader t1 = new PropertiesReader(properties);
        PropertiesReader t2 = new PropertiesReader(properties);
        PropertiesReader t3 = new PropertiesReader(properties);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        t1.getProperties().keySet().forEach(System.out::println);
        System.out.println(t2.getProperties().getProperty("1"));
        System.out.println(t3.getProperties().getProperty("2"));
    }

    @Test
    public void multipleThreadsReadFromSameFiles() throws Exception {
        File properties = new File("resourcesForTest.properties");
        File sameProperties = new File("resourcesForTest.properties");
        File anotherSameProperties = new File("resourcesForTest.properties");

        PropertiesReader t1 = new PropertiesReader(properties);
        PropertiesReader t2 = new PropertiesReader(sameProperties);
        PropertiesReader t3 = new PropertiesReader(anotherSameProperties);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        t1.getProperties().keySet().forEach(System.out::println);
        System.out.println(t2.getProperties().getProperty("1"));
        System.out.println(t3.getProperties().getProperty("2"));
    }

}