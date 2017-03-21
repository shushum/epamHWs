package com.epam.java.se.unit07.synchronizeTask;

import com.epam.java.se.unit07.OperationType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;


/**
 * Created by Yegor on 20.03.2017.
 */

/**
 * A thread class, designed to process bank operations from {@code NodeList} by its {@code OperationType} and save them in
 * {@code List<Operation>}.
 */
public class OperationSaver extends Thread {
    /**
     * Storage for processed bank operations.
     */
    private List<Operation> operationStorage;
    private final NodeList operations;
    private final OperationType operationType;
    /**
     * Amount of bank operations read in NodeList.
     */
    private final int amountOfOperations;

    /**
     * Creates a thread, that processes bank operations from this {@code NodeList} by required {@code OperationType} and save them in
     * specific {@code List<Operation>}
     * @param operationStorage {@code List<Operation>} for storing bank operations.
     * @param operations {@code NodeList} of bank operations.
     * @param operationType required {@code OperationType}.
     */
    public OperationSaver(List<Operation> operationStorage, NodeList operations, OperationType operationType) {
        this.operationStorage = operationStorage;
        this.operations = operations;
        this.operationType = operationType;
        amountOfOperations = operations.getLength();
    }

    public void run() {
        for (int i = 0; i < amountOfOperations; i++) {
            Operation operation = null;

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
                synchronized (operationStorage) {
                    operationStorage.add(new Operation(accountOwner, amount, operationType));
                    operationStorage.notify();
                }
            }
        }
    }
}
