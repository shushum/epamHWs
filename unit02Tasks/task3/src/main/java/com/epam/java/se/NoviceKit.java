package com.epam.java.se;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Yegor on 25.02.2017.
 */
public class NoviceKit {
    private ArrayList<Stationery> inventory;

    public NoviceKit(){
        inventory = new ArrayList<>(4);

        inventory.add(new BallPen(15, Colour.BLUE));
        inventory.add(new BallPen(15, Colour.BLACK));
        inventory.add(new Liner(30, Colour.YELLOW));
        inventory.add(new Ruler(15, 25));
    }

    public String accountingOfInventory(){
        StringBuilder message = new StringBuilder();

        message.append("List of Novice Kit:\n");
        for (int i = 0; i < inventory.size(); i++) {
            message.append("\t\t\t\t" + inventory.get(i).toString() + "\n");
        }

        return message.toString();
    }
}
