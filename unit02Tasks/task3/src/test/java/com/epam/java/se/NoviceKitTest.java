package com.epam.java.se;

import org.junit.Test;

/**
 * Created by Yegor on 25.02.2017.
 */
public class NoviceKitTest {
    @Test
    public void createNoviceKitAndListIt() throws Exception {
        NoviceKit john = new NoviceKit();
        System.out.println(john.accountingOfInventory());
    }
}