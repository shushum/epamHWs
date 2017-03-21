package com.epam.java.se.unit07.synchronizeTask;

import com.epam.java.se.unit07.OperationType;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 20.03.2017.
 */
public class OperationProceederTest {

    @Test
    public void runTest() throws Exception {
        List<Account> accounts = new ArrayList<>();

        Operation opFrom = new Operation("David", 10, OperationType.FROM);
        Operation opTo = new Operation("Collin", 10, OperationType.TO);

        List<Operation> operationStorage = new ArrayList<>();
        operationStorage.addAll(Collections.nCopies(100, opFrom));
        operationStorage.addAll(Collections.nCopies(100, opTo));
        Collections.shuffle(operationStorage);

        OperationProceeder op = new OperationProceeder(accounts, operationStorage);

        op.start();

        op.join();

        assertTrue(accounts.size() == 2);
        assertTrue(operationStorage.isEmpty());
        assertTrue(accounts.stream().mapToLong(Account::getBalance).reduce(Long::sum).getAsLong() == 200);
    }

    @Test
    public void synchronizeTest() throws Exception {
        List<Account> accounts = new ArrayList<>();

        Operation opFrom = new Operation("David", 10, OperationType.FROM);
        Operation opTo = new Operation("Collin", 10, OperationType.TO);

        List<Operation> operationStorage = new ArrayList<>();
        operationStorage.addAll(Collections.nCopies(100, opFrom));
        operationStorage.addAll(Collections.nCopies(100, opTo));
        Collections.shuffle(operationStorage);

        OperationProceeder op1 = new OperationProceeder(accounts, operationStorage);
        OperationProceeder op2 = new OperationProceeder(accounts, operationStorage);
        OperationProceeder op3 = new OperationProceeder(accounts, operationStorage);

        op1.start();
        op2.start();
        op3.start();

        op1.join();
        op2.join();
        op3.join();

        assertTrue(accounts.size() == 2);
        assertTrue(operationStorage.isEmpty());
        assertTrue(accounts.stream().mapToLong(Account::getBalance).reduce(Long::sum).getAsLong() == 200);
    }

}