package com.epam.java.se.unit07.rearchitecturing;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 20.03.2017.
 */
public class MainAccountTransactorTest {
    @Test
    public void processAllOperationsFromXMLToAccountStorage() throws Exception {
        MainAccountTransactor test = new MainAccountTransactor();

        test.processAllOperationsFromXMLToAccountStorage("123.xml");

        assertTrue(test.getAccountsStorage().size() == 4);
        assertTrue(test.getAccountsStorage().stream().mapToLong(Account::getBalance).reduce(Long::sum).getAsLong() == 400);
    }

}