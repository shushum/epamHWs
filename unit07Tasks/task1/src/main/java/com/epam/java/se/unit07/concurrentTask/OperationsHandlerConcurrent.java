package com.epam.java.se.unit07.concurrentTask;

import java.util.List;

/**
 * Created by Yegor on 17.03.2017.
 */
public class OperationsHandlerConcurrent implements Runnable {
    private List<OperationConcurrent> operations;
    private List<AccountConcurrent> accounts;
    private int startInclusiveIndex;
    private int endExclusiveIndex;

    public OperationsHandlerConcurrent(List<OperationConcurrent> operations, List<AccountConcurrent> accounts,
                                       int startInclusiveIndex, int endExclusiveIndex) {
        this.operations = operations;
        this.accounts = accounts;
        this.startInclusiveIndex = startInclusiveIndex;
        this.endExclusiveIndex = endExclusiveIndex;
    }

    public void run() {
        for (int i = startInclusiveIndex; i < endExclusiveIndex; i++) {
            OperationConcurrent operation = operations.get(i);

            handleOperation(operation);
        }
    }

    private void handleOperation(OperationConcurrent operation) {
        AccountConcurrent fromWho = accounts
                .stream()
                .filter(account -> account.equals(operation.getFromWho()))
                .findFirst().get();


        AccountConcurrent toWhom = accounts
                .stream()
                .filter(account -> account.equals(operation.getToWhom()))
                .findFirst().get();

        fromWho.withdraw(operation.getAmount());
        toWhom.deposit(operation.getAmount());
    }
}
