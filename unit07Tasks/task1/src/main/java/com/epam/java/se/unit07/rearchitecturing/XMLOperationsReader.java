package com.epam.java.se.unit07.rearchitecturing;

import com.epam.java.se.unit07.synchronizedTask.Operation;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Yegor on 20.03.2017.
 */
public class XMLOperationsReader {

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
