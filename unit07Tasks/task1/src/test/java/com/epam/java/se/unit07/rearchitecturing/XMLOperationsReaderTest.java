package com.epam.java.se.unit07.rearchitecturing;


import com.epam.java.se.unit07.XMLOperationsReader;
import org.junit.Test;
import org.w3c.dom.NodeList;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 15.03.2017.
 */
public class XMLOperationsReaderTest {
    @Test
    public void readExistingXML() throws Exception {
        NodeList test = XMLOperationsReader.readXML("123.xml");
        assertFalse(test.getLength() != 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void readNotExistingXML() throws Exception {
        XMLOperationsReader.readXML("notExisting.xml");
    }

    @Test(expected = IllegalArgumentException.class)
    public void readEmptyExistingXML() throws Exception {
        XMLOperationsReader.readXML("empty.xml");
    }

    @Test(expected = IllegalArgumentException.class)
    public void readNotXML() throws Exception {
        XMLOperationsReader.readXML("textFile.txt");
        //another fatal lul
    }

    @Test(expected = NullPointerException.class)
    public void readXMLWithoutFromToOrAmount() throws Exception {
        XMLOperationsReader.readXML("wrongType.xml");
    }

}