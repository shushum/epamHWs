package com.epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 24.02.2017.
 */
public class WorkplaceTest {

    @Test
    public void accountingOfStationery() throws Exception {
        Stationery[] stat1 = new Stationery[3];
        Stationery[] stat2 = new Stationery[3];
        for (int i = 0; i < 3; i++) {
            stat1[i] = new Stationery(i*10);
            stat2[i] = new Stationery(i*10);

        }
        Stationery stat3 = new Stationery(70);

        Workplace empty = new Workplace();
        Workplace john = new Workplace("John",stat1);
        Workplace peter = new Workplace("Peter",stat2);

        System.out.println(empty.accountingOfStationery());
        System.out.println(john.accountingOfStationery());
        System.out.println(peter.accountingOfStationery());

        empty.setOwner("Micky");
        empty.addStationery(stat1);
        empty.addStationery(stat3);
        john.addStationery(stat3);
        peter.addStationery(stat1);

        System.out.println(empty.accountingOfStationery());
        System.out.println(john.accountingOfStationery());
        System.out.println(peter.accountingOfStationery());

    }

}