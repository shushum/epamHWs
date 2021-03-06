package com.epam.java.se.unit07;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Yegor on 20.03.2017.
 */

/**
 * A class designed to parse information about bank operations from .xml file into NodeList.
 */
public class XMLOperationsReader {

    /**
     * Parses information about bank operations into NodeList.
     * @param path String path to .xml file.
     * @return NodeList of operations
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static NodeList readXML(String path) throws ParserConfigurationException, IOException, SAXException {
        File fileToParse = new File(path);

        if (fileToParse.length() == 0) {
            throw new IllegalArgumentException("This .xml file is empty!");
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(fileToParse);

        dbf.setNamespaceAware(true);
        document.getDocumentElement().normalize();

        return document.getElementsByTagName("operation");
    }
}
