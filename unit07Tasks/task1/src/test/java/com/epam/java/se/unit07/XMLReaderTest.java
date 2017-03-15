package com.epam.java.se.unit07;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 15.03.2017.
 */
public class XMLReaderTest {
    @Test
    public void readXML() throws Exception {
        XMLReader.readXML("123.xml");
    }

}