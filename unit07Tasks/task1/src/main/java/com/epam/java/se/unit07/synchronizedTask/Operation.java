package com.epam.java.se.unit07.synchronizedTask;

import com.epam.java.se.unit07.concurrentTask.AccountConcurrent;
import com.epam.java.se.unit07.synchronizedTask.Account;

/**
 * Created by Yegor on 15.03.2017.
 */
public class Operation {
    private final Account from;
    private final Account to;
    private final long amount;

    public Operation(String from, String to, long amount) {
        this.from = new Account(from);
        this.to = new Account(to);
        this.amount = amount;
    }

    public Account getFromWho() {
        return from;
    }

    public Account getToWhom() {
        return to;
    }

    public long getAmount() {
        return amount;
    }
}
