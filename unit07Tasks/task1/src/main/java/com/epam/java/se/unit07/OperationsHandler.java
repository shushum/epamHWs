package com.epam.java.se.unit07;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
            synchronized (accounts) {
                checkOnPresenceOfBothParticipantsInAccountList(operation);
            }

            handleOperation(operation);
            System.out.println("op succeeded "+ i);
        }
    }

    private void handleOperation(Operation operation) {
        Account fromWho = accounts
                .get(accounts.indexOf(operation.getFromWho()));

        Account toWhom = accounts
                .get(accounts.indexOf(operation.getToWhom()));

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

    private void checkOnPresenceOfBothParticipantsInAccountList(Operation operation) {
        Account fromWho = operation.getFromWho();
        Account toWhom = operation.getToWhom();

        if (!accounts.contains(fromWho)) {
            accounts.add(fromWho);
        } else if (!accounts.contains(toWhom)) {
            accounts.add(toWhom);
        }
    }
}
