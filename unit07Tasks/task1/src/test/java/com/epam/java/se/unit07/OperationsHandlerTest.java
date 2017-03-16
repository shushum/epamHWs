package com.epam.java.se.unit07;

import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 16.03.2017.
 */
public class OperationsHandlerTest {
    @Test
    public void synchronizeCheck() throws Exception {
        Operation op = new Operation("David", "Jake", 10);
        List<Operation> operations = Collections.nCopies(100, op);

        List<Account> result = new ArrayList<>();
        AccountsInvolvedInOperations.update(operations, result);

        OperationsHandler t1 = new OperationsHandler(operations, result, 0, operations.size() / 3);
        OperationsHandler t2 = new OperationsHandler(operations, result, operations.size() / 3, operations.size() * 2 / 3);
        OperationsHandler t3 = new OperationsHandler(operations, result, operations.size() * 2 / 3, operations.size());

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        assertTrue(result.get(0).getBalance() == -900);
        assertTrue(result.get(1).getBalance() == 1100);
    }

    @Test
    public void workWithEmptyOperationsList() throws Exception {
        List<Operation> operations = new ArrayList<>();

        List<Account> result = new ArrayList<>();

        OperationsHandler t1 = new OperationsHandler(operations, result, 0, operations.size() / 3);
        OperationsHandler t2 = new OperationsHandler(operations, result, operations.size() / 3, operations.size() * 2 / 3);
        OperationsHandler t3 = new OperationsHandler(operations, result, operations.size() * 2 / 3, operations.size());

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        //nothing really should happened though
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void workWithNotUpdatedAccountList() throws Exception {
        Operation op = new Operation("David", "Jake", 10);
        List<Operation> operations = Collections.nCopies(100, op);

        List<Account> result = new ArrayList<>();

        OperationsHandler t1 = new OperationsHandler(operations, result, 0, operations.size() / 3);
        OperationsHandler t2 = new OperationsHandler(operations, result, operations.size() / 3, operations.size() * 2 / 3);
        OperationsHandler t3 = new OperationsHandler(operations, result, operations.size() * 2 / 3, operations.size());

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        //assertion error though
    }
}