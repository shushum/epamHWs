package com.epam.java.se;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Yegor on 25.02.2017.
 */
public class NoviceKit implements Sorter {
    private ArrayList<Stationery> inventory;

    public NoviceKit() {
        inventory = new ArrayList<>(4);

        inventory.add(new BallPen(20, Colour.BLACK));
        inventory.add(new Ruler(15, 25));
        inventory.add(new Liner(30, Colour.YELLOW));
        inventory.add(new BallPen(15, Colour.BLUE));
    }

    public ArrayList<Stationery> getInventory() {
        return inventory;
    }

    public String accountingOfInventory() {
        StringBuilder message = new StringBuilder();

        message.append("List of Novice Kit:\n");
        for (int i = 0; i < inventory.size(); i++) {
            message.append("\t\t\t\t" + inventory.get(i).toString() + "\n");
        }

        return message.toString();
    }

    @Override
    public void sortByClassName(ArrayList<Stationery> sorted) {
        Collections.sort(sorted, (o1, o2) -> {
            String s1 = o1.getClass().getSimpleName();
            String s2 = o2.getClass().getSimpleName();
            return s1.compareTo(s2);
        });
    }

    @Override
    public void sortByPrice(ArrayList<Stationery> sorted) {
        Collections.sort(sorted, (o1, o2) -> {
            Integer p1 = o1.getPrice();
            Integer p2 = o2.getPrice();
            return p1.compareTo(p2);
        });
    }

    @Override
    public void sortByPriceAndClassName(ArrayList<Stationery> sorted) {
        Collections.sort(sorted, (o1, o2) -> {
            Integer p1 = o1.getPrice();
            Integer p2 = o2.getPrice();

            int result = p1.compareTo(p2);
            if (result != 0) {
                return result;
            }

            String s1 = o1.getClass().getSimpleName();
            String s2 = o2.getClass().getSimpleName();
            return s1.compareTo(s2);
        });
    }
}
