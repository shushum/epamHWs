package com.epam.java.se.unit07.concurrentTask;

import com.epam.java.se.unit07.OperationType;
import com.epam.java.se.unit07.XMLOperationsReader;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Yegor on 18.03.2017.
 */

/**
 * This thread class does same task as the MainAccountTransactor, but with java.util.concurrent.
 * @see com.epam.java.se.unit07.synchronizeTask.MainAccountTransactor
 */
public class MainAccountTransactorConcurrent {
    private AccountStorage clientAccounts;

    public MainAccountTransactorConcurrent() {
        clientAccounts = new AccountStorage(new ArrayList<>());
    }

    public MainAccountTransactorConcurrent(List<AccountConcurrent> clientAccounts) {
        this.clientAccounts = new AccountStorage(clientAccounts);
    }
    public void processAllOperationsFromXMLToAccountStorage(String pathToXMLFile)
            throws IOException, SAXException, ParserConfigurationException, InterruptedException {

        NodeList listOfOperations = XMLOperationsReader.readXML(pathToXMLFile);

        initSaversAndProceedersFor(listOfOperations);
    }

    public List<AccountConcurrent> getClientAccounts() {
        return clientAccounts.getAccountStorage();
    }

    private void initSaversAndProceedersFor(NodeList listOfOperations) throws InterruptedException {
        BlockingQueue<OperationConcurrent> operationsBuffer
                = new ArrayBlockingQueue<OperationConcurrent>(listOfOperations.getLength());

        ExecutorService ex = Executors.newFixedThreadPool(3);

        ex.execute(new OperationsSaverConcurrent(operationsBuffer, listOfOperations, OperationType.TO));
        ex.execute(new OperationsSaverConcurrent(operationsBuffer, listOfOperations, OperationType.FROM));

        ex.execute(new OperationProceederConcurrent(clientAccounts, operationsBuffer));
        ex.execute(new OperationProceederConcurrent(clientAccounts, operationsBuffer));

        ex.shutdown();

        try {
            ex.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
