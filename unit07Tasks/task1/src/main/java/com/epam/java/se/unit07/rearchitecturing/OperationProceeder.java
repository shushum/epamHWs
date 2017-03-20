package com.epam.java.se.unit07.rearchitecturing;

import com.epam.java.se.unit07.synchronizedTask.Account;

import java.util.List;
import java.util.Optional;

/**
 * Created by Yegor on 20.03.2017.
 */
public class OperationProceeder extends Thread {
    private List<Account> accounts;
    private List<Operation> operationsStorage;
    private boolean run;

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
