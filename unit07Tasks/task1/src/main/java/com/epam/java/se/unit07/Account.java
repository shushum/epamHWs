package com.epam.java.se.unit07;

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

    public void deposit(long val){
        balance += val;
    }

    public void withdraw(long val){
        balance -= val;
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
}
