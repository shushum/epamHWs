package com.epam.java.se;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 26.02.2017.
 */
public class SortByPriceAndClassNameTest {
    @Test
    public void customComparatorTest() throws Exception {
        NoviceKit kit = new NoviceKit();

        System.out.println(kit.accountingOfInventory());

        Collections.sort(kit.getInventory(), new SortByPriceAndClassName());

        System.out.println(kit.accountingOfInventory());
    }

}