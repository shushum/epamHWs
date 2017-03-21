package com.epam.java.se.unit07.concurrentTask;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 21.03.2017.
 */
public class MainAccountTransactorConcurrentTest {
    @Test
    public void processAllOperationsFromXMLToAccountStorage() throws Exception {
        MainAccountTransactorConcurrent test = new MainAccountTransactorConcurrent();

        test.processAllOperationsFromXMLToAccountStorage("123.xml");

        assertTrue(test.getClientAccounts().size() == 4);
        assertTrue(test.getClientAccounts()
                .stream()
                .mapToLong(AccountConcurrent::getBalance)
                .reduce(Long::sum)
                .getAsLong() == 400);
    }

}