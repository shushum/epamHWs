package com.epam.java.se;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class IntArrayListTest {
    @Test
    public void binarySearch() throws Exception {
        final int[] ints = {12, 0, -13, 666, 2, 56, 56, 56, 120, -2, 1, 0};
        final int[] expected = Arrays.copyOf(ints, ints.length);
        Arrays.sort(expected);

        final IntArrayList list = new IntArrayList(ints);

        list.sort();

        for (int i = 0; i < expected.length; i++) {
            assertEquals("i = " + i, expected[i], list.get(i));
        }


        System.out.println(list.binarySearch(-13));
        System.out.println(list.binarySearch(-15));
        System.out.println(list.binarySearch(120));
        System.out.println(list.binarySearch(666));
        System.out.println(list.binarySearch(667));
        System.out.println(list.binarySearch(0));
        System.out.println(list.binarySearch(-1));
        System.out.println(list.binarySearch(56));
        System.out.println(list.binarySearch(55));


    }

}