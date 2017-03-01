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
        for (int i = 0; i < 3; i++) {
            stat1[i] = new Stationery(i * 10);
        }

        Workplace john = new Workplace("John", stat1);

        assertTrue(john.accountingOfStationery().contains("Owner name: John|Stationery"));
        assertTrue(john.accountingOfStationery().contains("Price:0"));
        assertTrue(john.accountingOfStationery().contains("Price:20"));
        assertTrue(john.accountingOfStationery().contains("Total cost: 30"));
    }

    @Test
    public void accountingOfEmptyWorkplace() throws Exception {
        Workplace empty = new Workplace();
        assertTrue(empty.accountingOfStationery().contains("This workplace currently has no owner."));
    }

    @Test
    public void accountingOfEmptyStationery() throws Exception {
        Workplace empty = new Workplace();
        empty.setOwner("Micky");
        assertTrue(empty.accountingOfStationery().contains("This owner has no stationery."));
    }
    @Test
    public void addToStationery() throws Exception {
        Stationery[] stat1 = new Stationery[3];
        for (int i = 0; i < 3; i++) {
            stat1[i] = new Stationery(i * 10);
        }

        Workplace john = new Workplace("John", stat1);

        assertFalse(john.accountingOfStationery().contains("Price:70"));
        assertFalse(john.accountingOfStationery().contains("Total cost: 100"));

        Stationery stat3 = new Stationery(70);
        john.addStationery(stat3);

        assertTrue(john.accountingOfStationery().contains("Price:70"));
        assertTrue(john.accountingOfStationery().contains("Total cost: 100"));
    }
}