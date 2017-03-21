package com.epam.java.se.unit07.synchronizeTask;

import com.epam.java.se.unit07.OperationType;
import com.epam.java.se.unit07.XMLOperationsReader;
import org.junit.Test;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 20.03.2017.
 */
public class OperationSaverTest {
    @Test
    public void runTest() throws Exception {
        NodeList list = XMLOperationsReader.readXML("123.xml");

        List<Operation> operations = new ArrayList<>();

        OperationSaver oh = new OperationSaver(operations, list, OperationType.FROM);

        oh.run();

        assertTrue(operations.size() == list.getLength());
    }

    @Test
    public void multiThreadTest() throws Exception {
        NodeList list = XMLOperationsReader.readXML("123.xml");

        List<Operation> operations = new ArrayList<>();

        OperationSaver oh1 = new OperationSaver(operations, list, OperationType.FROM);
        OperationSaver oh2 = new OperationSaver(operations, list, OperationType.TO);

        oh1.start();
        oh2.start();

        oh1.join();
        oh2.join();

        assertTrue(operations.size() == list.getLength() * 2);
    }
}