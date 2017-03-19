package com.epam.java.se.unit07;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.channels.OverlappingFileLockException;
import java.util.Properties;
import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 19.03.2017.
 */
public class PropertiesReaderTest {
    private ExecutorService ex;

    @Before public void initExecutorService() throws Exception {
        ex = Executors.newFixedThreadPool(3);
    }

    @Test
    public void multipleThreadsReadFromOneFile() throws Exception {
        File properties = new File("resourcesForTest.properties");

        Future<Properties> p1 = ex.submit(new PropertiesReader(properties));
        Future<Properties> p2 = ex.submit(new PropertiesReader(properties));
        Future<Properties> p3 = ex.submit(new PropertiesReader(properties));

        Properties props1 = p1.get();
        Properties props2 = p2.get();
        Properties props3 = p3.get();

        props1.keySet().forEach(System.out::println);
        props2.keySet().forEach(System.out::println);
        props3.keySet().forEach(System.out::println);
    }

    @Test
    public void multipleThreadsReadFromSameFiles() throws Exception {
        File properties = new File("resourcesForTest.properties");
        File sameProperties = new File("resourcesForTest.properties");
        File anotherSameProperties = new File("resourcesForTest.properties");

        Future<Properties> p1 = ex.submit(new PropertiesReader(properties));
        Future<Properties> p2 = ex.submit(new PropertiesReader(sameProperties));
        Future<Properties> p3 = ex.submit(new PropertiesReader(anotherSameProperties));

        Properties props1 = p1.get();
        Properties props2 = p2.get();
        Properties props3 = p3.get();

        props1.keySet().forEach(System.out::println);
        props2.keySet().forEach(System.out::println);
        props3.keySet().forEach(System.out::println);
    }

    @Test
    public void getKeyFromFutureClass() throws Exception {
        File properties = new File("resourcesForTest.properties");

        Future<Properties> p1 = ex.submit(new PropertiesReader(properties));

        ElementsSupplier supplier = new ElementsSupplier(p1.get());

        assertTrue(supplier.getElementByKey("1").equals("element for key '1'"));
    }

}