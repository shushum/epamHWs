package com.epam.java.se.unit07.concurrentTask;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor on 18.03.2017.
 */
public class MainOperationHandlerConcurrent {
    private List<AccountConcurrent> clientAccounts;

    public MainOperationHandlerConcurrent() {
        clientAccounts = new ArrayList<>();
    }

    public MainOperationHandlerConcurrent(List<AccountConcurrent> clientAccounts) {
        this.clientAccounts = clientAccounts;
    }

    public void processOperationsFromXMLFile(String pathToXMLFile)
            throws ParserConfigurationException, SAXException, IOException, InterruptedException {

        List<OperationsConcurrent> operations = loadOperationsAndUpdateClientBase(pathToXMLFile);


    }

    private List<OperationsConcurrent> loadOperationsAndUpdateClientBase(String pathToXMLFile)
            throws IOException, SAXException, ParserConfigurationException {

        List<OperationsConcurrent> currentOperations = XMLOperationsReader.readXML(pathToXMLFile);
        AccountBaseUpdaterConcurrent.update(currentOperations, clientAccounts);

        return currentOperations;
    }
}
