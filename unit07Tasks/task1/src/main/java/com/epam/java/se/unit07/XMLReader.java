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
public class XMLReader {

    public static List<Operation> readXML(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(path));

        document.getDocumentElement().normalize();

        List<Operation> result = new ArrayList<>();
        NodeList nodes = document.getElementsByTagName("operation");
        System.out.println(nodes.getLength());
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element currentElement = (Element) node;

                String from = currentElement.getElementsByTagName("from").item(0).getTextContent();
                String to = currentElement.getElementsByTagName("to").item(0).getTextContent();
                long amount = Long.valueOf(currentElement.getElementsByTagName("amount").item(0).getTextContent());

                result.add(new Operation(from,to,amount));
            }
        }

        return result;
    }

}

