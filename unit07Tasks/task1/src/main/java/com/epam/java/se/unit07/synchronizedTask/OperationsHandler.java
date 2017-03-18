package com.epam.java.se.unit07.synchronizedTask;

import java.util.List;

/**
 * Created by Yegor on 16.03.2017.
 */
public class OperationsHandler extends Thread {
    private List<Operation> operations;
    private List<Account> accounts;
    private int startInclusiveIndex;
    private int endExclusiveIndex;

    public OperationsHandler(List<Operation> operations, List<Account> accounts, int startIndex, int endIndex) {
        this.operations = operations;
        this.accounts = accounts;
        startInclusiveIndex = startIndex;
        endExclusiveIndex = endIndex;
    }

    public void run() {
        for (int i = startInclusiveIndex; i < endExclusiveIndex; i++) {
            Operation operation = operations.get(i);

            handleOperation(operation);
        }
    }

    private void handleOperation(Operation operation) {
        Account fromWho = accounts
                .stream()
                .filter(account -> account.equals(operation.getFromWho()))
                .findFirst().get();


        Account toWhom = accounts
                .stream()
                .filter(account -> account.equals(operation.getToWhom()))
                .findFirst().get();

        Account acc1 = null, acc2 = null;

        if (fromWho.hashCode() < toWhom.hashCode()) {
            acc1 = fromWho;
            acc2 = toWhom;
        } else {
            acc1 = toWhom;
            acc2 = fromWho;
        }

        synchronized (acc1) {
            synchronized (acc2) {
                fromWho.withdraw(operation.getAmount());
                toWhom.deposit(operation.getAmount());
            }
        }
    }
}
