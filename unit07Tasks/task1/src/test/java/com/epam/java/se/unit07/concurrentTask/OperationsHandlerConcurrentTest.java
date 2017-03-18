package com.epam.java.se.unit07.concurrentTask;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 17.03.2017.
 */
public class OperationsHandlerConcurrentTest {
    @Test
    public void synchronizeCheck() throws Exception {
        OperationConcurrent op = new OperationConcurrent("David", "Jake", 10);
        OperationConcurrent op1 = new OperationConcurrent("Josh", "Peter", 10);
        OperationConcurrent op2 = new OperationConcurrent("David", "Peter", 10);

        List<OperationConcurrent> operations = new ArrayList<>();
        operations.addAll(Collections.nCopies(100, op));
        operations.addAll(Collections.nCopies(100, op1));
        operations.addAll(Collections.nCopies(100, op2));

        List<AccountConcurrent> result = new ArrayList<>();
        AccountBaseUpdaterConcurrent.update(operations, result);

        OperationsHandlerConcurrent r1 = new OperationsHandlerConcurrent(operations, result, 0, operations.size() / 3);
        OperationsHandlerConcurrent r2 = new OperationsHandlerConcurrent(operations, result, operations.size() / 3, operations.size() * 2 / 3);
        OperationsHandlerConcurrent r3 = new OperationsHandlerConcurrent(operations, result, operations.size() * 2 / 3, operations.size());

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        assertTrue(result.get(0).getBalance() == -1900);
        assertTrue(result.get(1).getBalance() == 1100);
        assertTrue(result.get(2).getBalance() == -900);
        assertTrue(result.get(3).getBalance() == 2100);
    }
}