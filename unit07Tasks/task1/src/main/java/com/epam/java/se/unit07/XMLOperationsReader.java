package com.epam.java.se.unit07;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yegor on 15.03.2017.
 */
public class XMLOperationsReader {

    public static List<Operation> readXML(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(path));

        document.getDocumentElement().normalize();

        List<Operation> result = new ArrayList<>();
        NodeList operations = document.getElementsByTagName("operation");

        for (int i = 0; i < operations.getLength(); i++) {
            Node operation = operations.item(i);

            if (operation.getNodeType() == Node.ELEMENT_NODE) {
                Element currentOperation = (Element) operation;

                String from = currentOperation.getElementsByTagName("from").item(0).getTextContent();
                String to = currentOperation.getElementsByTagName("to").item(0).getTextContent();
                long amount = Math.round(100 * Double.valueOf(currentOperation.getElementsByTagName("amount").item(0).getTextContent()));

                result.add(new Operation(from,to,amount));

            }
        }
        return result;
    }

}

