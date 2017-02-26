package com.epam.java.se;

import java.util.Comparator;

/**
 * Created by Yegor on 26.02.2017.
 */
public class SortByClassName implements Comparator<Stationery> {
    @Override
    public int compare(Stationery o1, Stationery o2) {
        return o1.getClass().getSimpleName().compareTo(o2.getClass().getSimpleName());
    }
}
