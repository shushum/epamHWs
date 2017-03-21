package com.epam.java.se.unit07.concurrentTask;

import com.epam.java.se.unit07.synchronizeTask.Account;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yegor on 18.03.2017.
 */
public class AccountConcurrent extends Account {
    private Lock lock = new ReentrantLock(true);

    public AccountConcurrent(String ownerName) {
        super(ownerName);
    }

    @Override
    public void deposit(long value) {

        lock.lock();
        try {
            super.deposit(value);
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void withdraw(long value) {

        lock.lock();
        try {
            super.withdraw(value);
        } finally {
            lock.unlock();
        }
    }
}
