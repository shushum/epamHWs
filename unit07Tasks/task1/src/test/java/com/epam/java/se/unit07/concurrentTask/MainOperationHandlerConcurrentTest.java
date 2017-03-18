package com.epam.java.se.unit07.concurrentTask;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 18.03.2017.
 */
public class MainOperationHandlerConcurrentTest {
    @Test
    public void executorServiceTest() throws Exception {
        MainOperationHandlerConcurrent main = new MainOperationHandlerConcurrent();
        main.processOperationsFromXMLFile("123.xml");

        List<AccountConcurrent> listOfAccounts = main.getClientAccounts();

        assertTrue(listOfAccounts.size() == 4);
        assertTrue(listOfAccounts.contains(new AccountConcurrent("Gambardella, Matthew")));
        assertTrue(listOfAccounts.contains(new AccountConcurrent("Midnight Rain")));

        assertTrue(listOfAccounts.get(0).getBalance() + listOfAccounts.get(1).getBalance() == 200);
        assertTrue(listOfAccounts.get(2).getBalance() + listOfAccounts.get(3).getBalance() == 200);
    }

}