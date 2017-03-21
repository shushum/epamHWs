package com.epam.java.se.unit07.synchronizeTask;

import java.util.List;
import java.util.Optional;

/**
 * Created by Yegor on 20.03.2017.
 */

/**
 * A thread class designed to handle bank operations to update state of the clients' Accounts.
 */
public class OperationProceeder extends Thread {
    /**
     * A list of known clients' Accounts. 'Known' in this case means the Account, that was involved in at least one operation
     * or was received from out source.
     */
    private List<Account> accounts;
    /**
     * A list of operations to handle.
     */
    private List<Operation> operationsStorage;
    private boolean run;

    /**
     * Creates a thread, that updates state of required {@code List<Account>} with this {@code List<Operation>}.
     * @param accounts {@code List<Account>} for updating.
     * @param operationsStorage {@code List<Operation>} to update {@code List<Account>} with.
     */
    public OperationProceeder(List<Account> accounts, List<Operation> operationsStorage) {
        this.accounts = accounts;
        this.operationsStorage = operationsStorage;
        run = true;
    }

    public void run() {

        while (run) {
            Operation currentOperation;

            synchronized (operationsStorage) {
                currentOperation = tryEjectOperationFromStorage();
            }

            if (currentOperation != null) {
                synchronized (accounts) {
                    proceedOperation(currentOperation);
                }
            } else {
                shutdownOperationProceeder();
            }
        }
    }

    private Operation tryEjectOperationFromStorage() {
        Operation currentOperation = getOperation();

        for (int i = 0; i < 10 && currentOperation == null; i++) {
            try {

                operationsStorage.wait(10);
                currentOperation = getOperation();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return currentOperation;
    }

    private void proceedOperation(Operation operation) {
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

    private void proceedOperationByType(Operation operation, Account currentAccount) {
        switch (operation.getOperationType()) {
            case FROM:
                currentAccount.withdraw(operation.getAmount());
                break;
            case TO:
                currentAccount.deposit(operation.getAmount());
                break;
        }
    }

    private Operation getOperation() {

        if (operationsStorage.size() > 0) {
            return operationsStorage.remove(0);
        }
        return null;
    }

    private void shutdownOperationProceeder() {
        run = false;
    }
}
