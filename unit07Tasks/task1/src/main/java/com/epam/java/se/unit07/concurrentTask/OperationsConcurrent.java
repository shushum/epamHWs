package com.epam.java.se.unit07.concurrentTask;

import com.epam.java.se.unit07.synchronizedTask.Account;

/**
 * Created by Yegor on 18.03.2017.
 */
public class OperationsConcurrent {
    private final AccountConcurrent from;
    private final AccountConcurrent to;
    private final long amount;

    public OperationsConcurrent(String from, String to, long amount) {
        this.from = new AccountConcurrent(from);
        this.to = new AccountConcurrent(to);
        this.amount = amount;
    }

    public AccountConcurrent getFromWho() {
        return from;
    }

    public AccountConcurrent getToWhom() {
        return to;
    }

    public long getAmount() {
        return amount;
    }
}
