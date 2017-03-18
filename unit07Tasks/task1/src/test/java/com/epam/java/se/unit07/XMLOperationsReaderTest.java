package com.epam.java.se.unit07;

import com.epam.java.se.unit07.synchronizedTask.Operation;
import com.epam.java.se.unit07.synchronizedTask.XMLOperationsReader;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 15.03.2017.
 */
public class XMLOperationsReaderTest {
    @Test
    public void readExistingXML() throws Exception {
        List<Operation> test = XMLOperationsReader.readXML("123.xml");
        assertFalse(test.isEmpty());
    }

    @Test(expected = FileNotFoundException.class)
    public void readNotExistingXML() throws Exception {
        XMLOperationsReader.readXML("notExisting.xml");
    }

    @Test(expected = IllegalArgumentException.class)
    public void readEmptyExistingXML() throws Exception {
        XMLOperationsReader.readXML("empty.xml");
    }

    @Test(expected = org.xml.sax.SAXParseException.class)
    public void readNotXML() throws Exception {
        XMLOperationsReader.readXML("textFile.txt");
        //another fatal lul
    }

    @Test(expected = NullPointerException.class)
    public void readXMLWithoutFromToOrAmount() throws Exception {
        XMLOperationsReader.readXML("wrongType.xml");
    }

}