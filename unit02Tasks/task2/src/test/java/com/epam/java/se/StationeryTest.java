package com.epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 24.02.2017.
 */
public class StationeryTest {
    @Test
    public void priceTest() throws Exception {
        Stationery stat = new Stationery();
        System.out.println(stat.getPrice());

        Stationery preciousStat = new Stationery(10);
        System.out.println(preciousStat.getPrice());

        assertFalse(stat.equals(preciousStat));
        assertTrue(stat.equals(stat));
        assertTrue(preciousStat.equals(preciousStat));
    }

    @Test
    public void toStringTest() throws Exception {
        Stationery stat = new Stationery();
        System.out.println(stat.toString());

        Stationery preciousStat = new Stationery(10);
        System.out.println(preciousStat.toString());
    }


}
