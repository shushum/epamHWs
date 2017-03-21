package com.epam.java.se.unit07.synchronizeTask;

import com.epam.java.se.unit07.OperationType;

/**
 * Created by Yegor on 20.03.2017.
 */
public class Operation {
    private Account account;
    private long amount;
    private OperationType operationType;

    public Operation(String ownerName, long amount, OperationType operationType) {
        account = new Account(ownerName);
        this.amount = amount;
        this.operationType = operationType;
    }

    public Account getAccount() {
        return account;
    }

    public long getAmount() {
        return amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }
}
