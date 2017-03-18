package com.epam.java.se.unit07.concurrentTask;

import com.epam.java.se.unit07.synchronizedTask.Operation;

import java.util.List;

/**
 * Created by Yegor on 17.03.2017.
 */
public class OperationHandlerConcurrent extends Thread {
    private List<OperationsConcurrent> operations;
    private List<AccountConcurrent> accounts;
    private int startInclusiveIndex;
    private int endExclusiveIndex;

    public OperationHandlerConcurrent(List<OperationsConcurrent> operations, List<AccountConcurrent> accounts, int startInclusiveIndex, int endExclusiveIndex) {
        this.operations = operations;
        this.accounts = accounts;
        this.startInclusiveIndex = startInclusiveIndex;
        this.endExclusiveIndex = endExclusiveIndex;
    }

    public void run() {
        for (int i = startInclusiveIndex; i < endExclusiveIndex; i++) {
            OperationsConcurrent operation = operations.get(i);

            handleOperation(operation);
            System.out.println(i);
        }
    }

    private void handleOperation(OperationsConcurrent operation) {
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
