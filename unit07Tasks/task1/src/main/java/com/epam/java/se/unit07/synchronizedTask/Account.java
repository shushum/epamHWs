package com.epam.java.se.unit07.synchronizedTask;

import java.util.Objects;

/**
 * Created by Yegor on 16.03.2017.
 */
public class Account {
    private final String ownerName;
    private long balance;

    public Account(String ownerName) {
        Objects.requireNonNull(ownerName);

        this.ownerName = ownerName;
        balance = 100L;
    }

    public void deposit(long value) {
        valueIsPositive(value);

        balance += value;
    }

    public void withdraw(long value) {
        valueIsPositive(value);

        balance -= value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return ownerName.equals(account.ownerName);
    }

    @Override
    public int hashCode() {
        return ownerName.hashCode();
    }

    public String getCurrentState() {
        long dollars = balance / 100;
        long cents = Math.abs(balance % 100);
        return String.format("Account of %s for now has %d.%d dollars.", ownerName, dollars, cents);
    }

    public long getBalance() {
        return balance;
    }

    private void valueIsPositive(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("Can't operate with negative values!");
        }
    }
}
