package com.epam.java.se;

import java.util.Comparator;

/**
 * Created by Yegor on 26.02.2017.
 */
public class SortByPriceAndClassName implements Comparator<Stationery> {
    @Override
    public int compare(Stationery o1, Stationery o2) {
        int result = o1.getPrice() - o2.getPrice();

        if (result != 0) {
            return result;
        }

        return o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
    }
}
