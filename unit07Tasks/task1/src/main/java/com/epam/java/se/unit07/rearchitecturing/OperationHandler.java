package com.epam.java.se.unit07.rearchitecturing;

import com.epam.java.se.unit07.synchronizedTask.Account;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Created by Yegor on 20.03.2017.
 */
public class OperationHandler extends Thread {
    private List<Account> accounts;
    private final NodeList operations;
    private final OperationType operationType;

    public OperationHandler(ArrayList<Account> accounts, NodeList operations, OperationType operationType) {
        this.accounts = accounts;
        this.operations = operations;
        this.operationType = operationType;
    }

    public void run() {
        for (int i = 0; i < operations.getLength(); i++) {
            Operation operation = null;

            synchronized (operations) {
                Node node = operations.item(i);

                try {
                    operation = ejectOperation(node);
                } catch (IllegalArgumentException e) {
                    //might be logged
                }
            }

            synchronized (accounts) {
                proceedOperation(operation);
            }
        }
    }

    private void proceedOperation(Operation operation) {
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
    }

    private Operation ejectOperation(Node node) {

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element currentOperation = (Element) node;

            String accountOwner =
                    currentOperation.getElementsByTagName(operationType.getOperationType()).item(0).getTextContent();
            long amount
                    = Math.round(100 * Double.valueOf(currentOperation.getElementsByTagName("amount").item(0).getTextContent()));

            if (amount <= 0) {
                throw new IllegalArgumentException();
            }
            return new Operation(accountOwner, amount, operationType);

        } else {
            throw new IllegalArgumentException();  //todo pass return on not ELEMENT_NOD
        }
    }
}
