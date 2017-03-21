package com.epam.java.se.unit07.concurrentTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yegor on 21.03.2017.
 */

/**
 * This thread class does same task as the OperationProceeder, but with java.util.concurrent.
 * @see com.epam.java.se.unit07.synchronizeTask.OperationProceeder
 */
public class OperationProceederConcurrent extends Thread{
    private AccountStorage accounts;
    private BlockingQueue<OperationConcurrent> operationsStorage;
    private boolean run;

    public OperationProceederConcurrent(AccountStorage accounts, BlockingQueue<OperationConcurrent> operationsStorage) {
        this.accounts = accounts;
        this.operationsStorage = operationsStorage;
        run = true;
    }

    public void run() {

        while (run) {
            OperationConcurrent currentOperation;

            currentOperation = tryEjectOperationFromStorage();

            if (currentOperation != null) {
                proceedOperation(currentOperation);
            } else {
                shutdownOperationProceeder();
            }
        }
    }

    private OperationConcurrent tryEjectOperationFromStorage() {
        OperationConcurrent currentOperation = operationsStorage.poll();

        for (int i = 0; i < 10 && currentOperation == null; i++) {
            try {

                currentOperation = operationsStorage.poll(10, TimeUnit.MILLISECONDS);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return currentOperation;
    }

    private void proceedOperation(OperationConcurrent operation) {
        AccountConcurrent currentAccount = accounts.getOrAddAndGetIfAbsent(operation.getAccount());

        proceedOperationByType(operation, currentAccount);
    }

    private void proceedOperationByType(OperationConcurrent operation, AccountConcurrent currentAccount) {
        switch (operation.getOperationType()) {
            case FROM:
                currentAccount.withdraw(operation.getAmount());
                break;
            case TO:
                currentAccount.deposit(operation.getAmount());
                break;
        }
    }

    private void shutdownOperationProceeder() {
        run = false;
    }
}
