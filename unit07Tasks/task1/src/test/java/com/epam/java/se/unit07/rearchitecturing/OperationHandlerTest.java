package com.epam.java.se.unit07.rearchitecturing;

import com.epam.java.se.unit07.synchronizedTask.Account;
import org.junit.Test;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 20.03.2017.
 */
public class OperationHandlerTest {
    @Test
    public void runTest() throws Exception {
        NodeList list = XMLOperationsReader.readXML("123.xml");

        ArrayList<Account> accounts = new ArrayList<>();

        OperationHandler oh = new OperationHandler(accounts, list, OperationType.FROM);

        oh.run();

        assertTrue( accounts.size() == 2);
    }

    @Test
    public void multiThreadTest() throws Exception {
        NodeList list = XMLOperationsReader.readXML("123.xml");

        ArrayList<Account> accounts = new ArrayList<>();

        OperationHandler oh1 = new OperationHandler(accounts, list, OperationType.FROM);
        OperationHandler oh2 = new OperationHandler(accounts, list, OperationType.TO);

        oh1.start();
        oh2.start();

        oh1.join();
        oh2.join();

    //    assertTrue( accounts.size() == 4);
        accounts.forEach(account -> System.out.println(account.getCurrentState()));
    }

}