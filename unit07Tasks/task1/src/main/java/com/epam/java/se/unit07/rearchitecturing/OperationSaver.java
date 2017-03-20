package com.epam.java.se.unit07.rearchitecturing;

import com.epam.java.se.unit07.synchronizedTask.Account;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;
import java.util.Optional;


/**
 * Created by Yegor on 20.03.2017.
 */
public class OperationSaver extends Thread {
    private List<Operation> operationStorage;
    private final NodeList operations;
    private final OperationType operationType;
    private final int amountOfOperations;

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

          /*  synchronized (accounts) {
                proceedOperation(operation);
            }*/
        }
    }

 /*   private void proceedOperation(Operation operation) {
        if (operation != null) {

            Optional<Account> optionalAccount = accounts
                    .stream()
                    .filter(accountInList -> accountInList.equals(operation.getAccount()))
                    .findFirst();

            if (optionalAccount.isPresent()) {

                proceedOperationByType(operation, optionalAccount.get());

            } else {

                Account newAccountInList = operation.getAccount();

                proceedOperationByType(operation, newAccountInList);

                accounts.add(newAccountInList);
            }
        }
    }

    private void proceedOperationByType(Operation operation, Account currentAccount) {
        switch (operationType) {
            case FROM:
                currentAccount.withdraw(operation.getAmount());
                break;
            case TO:
                currentAccount.deposit(operation.getAmount());
                break;
        }
    }*/

    private void storeOperation(Node node) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element currentOperation = (Element) node;

            String accountOwner =
                    currentOperation.getElementsByTagName(operationType.getOperationType()).item(0).getTextContent();
            long amount
                    = Math.round(100 * Double.valueOf(currentOperation.getElementsByTagName("amount").item(0).getTextContent()));

            if (amount > 0) {
                operationStorage.add(new Operation(accountOwner, amount, operationType));
            }
        }
    }
}
