package com.epam.java.se.unit07.synchronizeTask;

import java.util.Objects;

/**
 * Created by Yegor on 16.03.2017.
 */

/**
 * A class designed to store and modify information about specific Account current state.
 */
public class Account {
    /**
     * Unique name of Account holder.
     */
    private final String ownerName;
    /**
     * Current amount of money in the Account. Can be negative.
     */
    private long balance;

    /**
     * Creates an instance of Account with required holder name and base amount of money.
     * @param ownerName name of Account holder.
     */
    public Account(String ownerName) {
        Objects.requireNonNull(ownerName);

        this.ownerName = ownerName;
        balance = 100L;
    }

    /**
     * Deposits the required amount of money in Account.
     * @param value required amount of money. Must be positive.
     */
    public void deposit(long value) {
        valueIsPositive(value);

        balance += value;
    }

    /**
     * Withdraws the required amount of money from Account.
     * @param value required amount of money. Must be positive.
     */
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
