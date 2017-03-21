package com.epam.java.se.unit07.synchronizeTask;

import com.epam.java.se.unit07.OperationType;

/**
 * Created by Yegor on 20.03.2017.
 */

/**
 * A class designed to store information about participation of specific account in single bank operation.
 */
public class Operation {
    /**
     * Account participated in the bank operation.
     */
    private Account account;
    /**
     * Amount of money involved in the bank operation.
     */
    private long amount;
    /**
     * Type of the bank operation (a.k.a. deposit or withdraw) for this account.
     */
    private OperationType operationType;

    /**
     * Creates an Operation unit, that stores information about specific single bank operation.
     * @param ownerName Identifier for account holder.
     * @param amount amount of money involved in bank operation.
     * @param operationType type of the bank operation.
     */
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
