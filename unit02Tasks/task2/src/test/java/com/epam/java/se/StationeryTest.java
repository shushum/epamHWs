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
        assertTrue(stat.getPrice() == 0);


        Stationery preciousStat = new Stationery(10);
        assertTrue(stat.getPrice() == 10);

        assertFalse(stat.equals(preciousStat));
        assertTrue(stat.equals(stat));
        assertTrue(preciousStat.equals(preciousStat));
    }

    @Test
    public void toStringTest() throws Exception {
        Stationery stat = new Stationery();
        assertTrue(stat.toString().contains("Stationery"));
        assertTrue(stat.toString().contains("Price:0"));

        Stationery preciousStat = new Stationery(10);
        assertTrue(preciousStat.toString().contains("Stationery"));
        assertTrue(preciousStat.toString().contains("Price:10"));
    }


}
