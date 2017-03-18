package com.epam.java.se.unit07.concurrentTask;

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
 * Created by Yegor on 18.03.2017.
 */
public class XMLOperationsReader {

    public static List<OperationConcurrent> readXML(String path) throws ParserConfigurationException, IOException, SAXException {
        File fileToParse = new File(path);
        if (fileToParse.length() == 0) {
            throw new IllegalArgumentException("This .xml file is empty!");
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(fileToParse);

        dbf.setNamespaceAware(true);
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("operation");

        List<OperationConcurrent> operations = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            ejectOperation(operations, node);
        }
        return operations;
    }

    private static void ejectOperation(List<OperationConcurrent> operations, Node node) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element currentOperation = (Element) node;

            String from = currentOperation.getElementsByTagName("from").item(0).getTextContent();
            String to = currentOperation.getElementsByTagName("to").item(0).getTextContent();
            long amount = Math.round(100 * Double.valueOf(currentOperation.getElementsByTagName("amount").item(0).getTextContent()));

            if (amount > 0) {
                operations.add(new OperationConcurrent(from, to, amount));
            }
        }
    }

}
