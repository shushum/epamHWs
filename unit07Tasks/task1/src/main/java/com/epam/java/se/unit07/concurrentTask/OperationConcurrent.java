package com.epam.java.se.unit07.concurrentTask;

import com.epam.java.se.unit07.OperationType;

/**
 * Created by Yegor on 18.03.2017.
 */
public class OperationConcurrent {
    private final AccountConcurrent account;
    private final long amount;
    private OperationType operationType;

    public OperationConcurrent(String ownerName, long amount, OperationType operationType) {
        account = new AccountConcurrent(ownerName);
        this.amount = amount;
        this.operationType = operationType;
    }

    public AccountConcurrent getAccount() {
        return account;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public long getAmount() {
        return amount;
    }
}
