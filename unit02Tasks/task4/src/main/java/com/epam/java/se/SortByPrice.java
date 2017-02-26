package com.epam.java.se;

import java.util.Comparator;

/**
 * Created by Yegor on 26.02.2017.
 */
public class SortByPrice implements Comparator<Stationery> {

    @Override
    public int compare(Stationery o1, Stationery o2) {
        return o1.getPrice() - o2.getPrice();
    }
}
