package com.epam.java.se.unit07.concurrentTask;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yegor on 21.03.2017.
 */
public class AccountStorage {
    private List<AccountConcurrent> accountStorage;
    private Lock lock;

    public AccountStorage(List<AccountConcurrent> accountStorage) {
        this.accountStorage = accountStorage;
        lock = new ReentrantLock();
    }

    public AccountConcurrent getOrAddAndGetIfAbsent(AccountConcurrent accountToCheck) {

        lock.lock();
        try {
            Optional<AccountConcurrent> optionalAccount = accountStorage
                    .stream()
                    .filter(account -> account.equals(accountToCheck))
                    .findFirst();

            if (optionalAccount.isPresent()) {
                return optionalAccount.get();
            } else {
                accountStorage.add(accountToCheck);
                return accountToCheck;
            }
        } finally {
            lock.unlock();
        }
    }

    public List<AccountConcurrent> getAccountStorage() {
        lock.lock();
        try {
            return accountStorage;
        } finally {
            lock.unlock();
        }
    }
}
