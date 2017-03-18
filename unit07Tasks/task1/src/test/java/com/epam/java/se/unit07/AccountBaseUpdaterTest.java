package com.epam.java.se.unit07;

import com.epam.java.se.unit07.synchronizedTask.Account;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 16.03.2017.
 */
public class AccountBaseUpdaterTest {
    @Test
    public void updateEmptyAccountList() throws Exception {
        Operation op = new Operation("David", "Jake", 10);
        List<Operation> operations = Collections.nCopies(100, op);

        List<Account> accountList = new ArrayList<>();
        AccountBaseUpdater.update(operations, accountList);

        assertTrue(accountList.size() == 2);
    }

    @Test
    public void updateAccountList() throws Exception {
        Operation op = new Operation("David", "Jake", 10);
        List<Operation> operations = Collections.nCopies(100, op);

        List<Account> accountList = new ArrayList<>();
        AccountBaseUpdater.update(operations, accountList);

        List<Operation> newOperations = new ArrayList<>();
        newOperations.add(new Operation("Peter", "Jacob", 100));

        AccountBaseUpdater.update(newOperations, accountList);

        assertTrue(accountList.size() == 4);
    }

}