package com.epam.java.se.unit07.synchronizeTask;

import com.epam.java.se.unit07.OperationType;
import com.epam.java.se.unit07.XMLOperationsReader;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor on 20.03.2017.
 */

/**
 * Assembling the threads, that will handle bank operations from .xml file to {@code List<Account>}.
 */
public class MainAccountTransactor {
    /**
     * A list of known clients' Accounts. 'Known' in this case means the Account, that was involved in at least one operation
     * or was received from out source.
     */
    private List<Account> accountsStorage;

    /**
     * Creates an empty {@code List<Account>} as storage.
     */
    public MainAccountTransactor() {
        accountsStorage = new ArrayList<>();
    }

    /**
     * Sets premade {@code List<Account>} as storage.
     */
    public MainAccountTransactor(List<Account> initialAccounts) {
        accountsStorage = initialAccounts;
    }

    /**
     * Handles bank operations in multi threads.
     * @param pathToXMLFile .xml file of bank operations
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws InterruptedException
     */
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
