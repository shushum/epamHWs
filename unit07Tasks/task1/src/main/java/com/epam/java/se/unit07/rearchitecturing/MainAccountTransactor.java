package com.epam.java.se.unit07.rearchitecturing;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor on 20.03.2017.
 */
public class MainAccountTransactor {
    private List<Account> accountsStorage;

    public MainAccountTransactor() {
        accountsStorage = new ArrayList<>();
    }

    public MainAccountTransactor(List<Account> initialAccounts) {
        accountsStorage = initialAccounts;
    }

    public void processAllOperationsFromXMLToAccountStorage(String pathToXMLFile)
            throws IOException, SAXException, ParserConfigurationException, InterruptedException {

        NodeList listOfOperations = XMLOperationsReader.readXML(pathToXMLFile);

        initSaversAndProceedersFor(listOfOperations);
    }

    public List<Account> getAccountsStorage() {
        return accountsStorage;
    }

    private void initSaversAndProceedersFor(NodeList listOfOperations) throws InterruptedException {
        List<Operation> operationsBuffer = new ArrayList<>();

        OperationSaver fromSaver = new OperationSaver(operationsBuffer, listOfOperations, OperationType.FROM);
        OperationSaver toSaver = new OperationSaver(operationsBuffer, listOfOperations, OperationType.TO);

        OperationProceeder op1 = new OperationProceeder(accountsStorage, operationsBuffer);
        OperationProceeder op2 = new OperationProceeder(accountsStorage, operationsBuffer);

        fromSaver.start();
        toSaver.start();
        op1.start();
        op2.start();

        fromSaver.join();
        toSaver.join();
        op1.join();
        op2.join();
    }
}
