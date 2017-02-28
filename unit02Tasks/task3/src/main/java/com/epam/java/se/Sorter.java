package com.epam.java.se;

import java.util.ArrayList;

/**
 * Created by Yegor on 28.02.2017.
 */
public interface Sorter {

    void sortByClassName(ArrayList<Stationery> sorted);

    void sortByPrice(ArrayList<Stationery> sorted);

    void sortByPriceAndClassName(ArrayList<Stationery> sorted);
}
