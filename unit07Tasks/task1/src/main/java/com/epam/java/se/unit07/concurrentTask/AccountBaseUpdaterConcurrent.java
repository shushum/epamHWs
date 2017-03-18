package com.epam.java.se.unit07.concurrentTask;

import java.util.List;

/**
 * Created by Yegor on 18.03.2017.
 */
public class AccountBaseUpdaterConcurrent {

    public static void update(List<OperationsConcurrent> operations, List<AccountConcurrent> previousVersion) {

        for (OperationsConcurrent operation : operations) {

            AccountConcurrent fromWho = operation.getFromWho();
            AccountConcurrent toWhom = operation.getToWhom();

            if (!previousVersion.contains(fromWho)) {
                previousVersion.add(fromWho);
            }

            if (!previousVersion.contains(toWhom)) {
                previousVersion.add(toWhom);
            }
        }
    }
}
