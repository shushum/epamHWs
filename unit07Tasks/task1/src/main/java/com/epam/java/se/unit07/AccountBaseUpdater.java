package com.epam.java.se.unit07;

import com.epam.java.se.unit07.synchronizedTask.Account;

import java.util.List;

/**
 * Created by Yegor on 16.03.2017.
 */
public class AccountBaseUpdater {

    public static void update(List<Operation> operations, List<Account> previousVersion) {

        for (Operation operation : operations) {

            Account fromWho = operation.getFromWho();
            Account toWhom = operation.getToWhom();

            if (!previousVersion.contains(fromWho)) {
                previousVersion.add(fromWho);
            }

            if (!previousVersion.contains(toWhom)) {
                previousVersion.add(toWhom);
            }
        }
    }
}
