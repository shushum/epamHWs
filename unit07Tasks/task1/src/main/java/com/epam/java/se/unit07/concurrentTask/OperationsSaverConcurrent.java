package com.epam.java.se.unit07.concurrentTask;

import com.epam.java.se.unit07.OperationType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Yegor on 17.03.2017.
 */
public class OperationsSaverConcurrent implements Runnable {
    private BlockingQueue<OperationConcurrent> operationStorage;
    private final NodeList operations;
    private final OperationType operationType;
    private final int amountOfOperations;

    public OperationsSaverConcurrent(BlockingQueue<OperationConcurrent> operationStorage, NodeList operations, OperationType operationType) {
        this.operationStorage = operationStorage;
        this.operations = operations;
        this.operationType = operationType;
        amountOfOperations = operations.getLength();
    }

    public void run() {
        for (int i = 0; i < amountOfOperations; i++) {
            OperationConcurrent operation = null;

            synchronized (operations) {
                Node node = operations.item(i);

                storeOperation(node);
            }
        }
    }

    private void storeOperation(Node node) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element currentOperation = (Element) node;

            String accountOwner =
                    currentOperation.getElementsByTagName(operationType.getOperationType()).item(0).getTextContent();
            long amount
                    = Math.round(100 * Double.valueOf(currentOperation.getElementsByTagName("amount").item(0).getTextContent()));

            if (amount > 0) {
                try {
                    operationStorage.put(new OperationConcurrent(accountOwner, amount, operationType));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
