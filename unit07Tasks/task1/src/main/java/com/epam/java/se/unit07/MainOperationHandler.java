package com.epam.java.se.unit07;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor on 16.03.2017.
 */
public class MainOperationHandler {
    private List<Account> clientAccounts;

    public MainOperationHandler() {
        clientAccounts = new ArrayList<>();
    }

    public MainOperationHandler(List<Account> clientAccounts) {
        this.clientAccounts = clientAccounts;
    }

    public void processOperationsFromXMLFile(String pathToXMLFile)
            throws ParserConfigurationException, SAXException, IOException, InterruptedException {

        List<Operation> operations = loadOperationsAndUpdateClientBase(pathToXMLFile);

        OperationsHandler oh1 = new OperationsHandler(operations, clientAccounts, 0, operations.size() / 3);
        OperationsHandler oh2 = new OperationsHandler(operations, clientAccounts, operations.size() / 3, operations.size() / 3 * 2);
        OperationsHandler oh3 = new OperationsHandler(operations, clientAccounts, operations.size() / 3 * 2, operations.size());

        oh1.start();
        oh2.start();
        oh3.start();

        oh1.join();
        oh2.join();
        oh3.join();
    }

    private List<Operation> loadOperationsAndUpdateClientBase(String pathToXMLFile)
            throws IOException, SAXException, ParserConfigurationException {

        List<Operation> currentOperations = XMLOperationsReader.readXML(pathToXMLFile);
        AccountBaseUpdater.update(currentOperations, clientAccounts);

        return currentOperations;
    }
}
