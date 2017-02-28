package com.epam.java.se;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * Created by Yegor on 28.02.2017.
 */
public class TestsForNewVersionOfTask4 {


    @Test
    public void sortByClassName() throws Exception {

        NoviceKit kit = new NoviceKit();
        kit.sortByClassName(kit.getInventory());

        for (int i = 1; i < kit.getInventory().size(); i++) {
            assertTrue(kit.getInventory().get(i - 1).getClass().getSimpleName().compareTo(
                    kit.getInventory().get(i).getClass().getSimpleName()) <= 0);
        }

    }

    @Test
    public void sortByPrice() throws Exception {

        NoviceKit kit = new NoviceKit();
        kit.sortByPrice(kit.getInventory());

        for (int i = 1; i < kit.getInventory().size(); i++) {
            assertTrue(kit.getInventory().get(i - 1).getPrice() <=
                    kit.getInventory().get(i).getPrice());
        }

    }


    @Test
    public void sortByPriceAndClassName() throws Exception {

        NoviceKit kit = new NoviceKit();
        NoviceKit priceKit = new NoviceKit();

        priceKit.sortByPrice(priceKit.getInventory());

        kit.sortByPriceAndClassName(kit.getInventory());


        for (int i = 0; i < kit.getInventory().size() - 1; i++) {
            assertEquals(kit.getInventory().get(i).getPrice(), priceKit.getInventory().get(i).getPrice());

            if (kit.getInventory().get(i).getPrice() == kit.getInventory().get(i + 1).getPrice()) {

                assertTrue(kit.getInventory().get(i).getClass().getSimpleName().compareTo(
                        kit.getInventory().get(i + 1).getClass().getSimpleName()) <= 0);
            }
        }

    }
}
