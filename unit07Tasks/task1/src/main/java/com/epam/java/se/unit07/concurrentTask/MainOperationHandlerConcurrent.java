package com.epam.java.se.unit07.concurrentTask;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

        ExecutorService ex = Executors.newFixedThreadPool(3);

        ex.execute(new OperationHandlerConcurrent(operations, clientAccounts,
                0, operations.size() / 3));
        ex.execute(new OperationHandlerConcurrent(operations, clientAccounts,
                operations.size() / 3, operations.size() * 2 / 3));
        ex.execute(new OperationHandlerConcurrent(operations, clientAccounts,
                operations.size() * 2 / 3, operations.size()));

        ex.shutdown();

        try {
            ex.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<AccountConcurrent> getClientAccounts() {
        return clientAccounts;
    }

    private List<OperationsConcurrent> loadOperationsAndUpdateClientBase(String pathToXMLFile)
            throws IOException, SAXException, ParserConfigurationException {

        List<OperationsConcurrent> currentOperations = XMLOperationsReader.readXML(pathToXMLFile);
        AccountBaseUpdaterConcurrent.update(currentOperations, clientAccounts);

        return currentOperations;
    }
}
