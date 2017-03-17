package com.epam.java.se.unit07.concurrentTask;

import com.epam.java.se.unit07.Account;
import com.epam.java.se.unit07.AccountBaseUpdater;
import com.epam.java.se.unit07.Operation;
import com.epam.java.se.unit07.synchronizedTask.OperationsHandler;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 17.03.2017.
 */
public class OperationHandlerConcurrentTest {
    @Test
    public void synchronizeCheck() throws Exception {
        Operation op = new Operation("David", "Jake", 10);
        Operation op1 = new Operation("Josh", "Peter",10);
        Operation op2 = new Operation("David", "Peter",10);
        List<Operation> operations = new ArrayList<>();
        operations.addAll(Collections.nCopies(100, op));
        operations.addAll(Collections.nCopies(100, op1));
        operations.addAll(Collections.nCopies(100, op2));

        List<Account> result = new ArrayList<>();
        AccountBaseUpdater.update(operations, result);

        OperationHandlerConcurrent t1 = new OperationHandlerConcurrent(operations, result, 0, operations.size() / 3);
        OperationHandlerConcurrent t2 = new OperationHandlerConcurrent(operations, result, operations.size() / 3, operations.size() * 2 / 3);
        OperationHandlerConcurrent t3 = new OperationHandlerConcurrent(operations, result, operations.size() * 2 / 3, operations.size());

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        for (Account acc : result) {
            System.out.println(acc.getCurrentState());
        }
       // assertTrue(result.get(0).getBalance() == -900);
       // assertTrue(result.get(1).getBalance() == 1100);
    }
}