package com.epam.java.se;

import java.util.Arrays;

/**
 * Created by Yegor on 24.02.2017.
 */
public class Workplace {
    private String owner;
    private int size;
    private Stationery[] stationery;

    public Workplace() {
        owner = "No owner";
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Workplace(String owner, Stationery[] belongings) {
        this.owner = owner;
        size = belongings.length;
        stationery = belongings;
    }

    public String accountingOfStationery() {
        if (owner.equals("No owner")) {
            String message = "This workplace currently has no owner.";
            return message;
        } else {
            StringBuilder message = new StringBuilder();
            message.append("Owner name: " + owner + "| Stationary:\n");
            for (int i = 0; i < stationery.length; i++){
                message.append(stationery[i].toString()+"\n");
            }
            message.append("Total cost: " + totalCost());
            return message.toString();
        }
    }

    private int totalCost() {
        int totalCost = 0;
        for (int i=0; i<stationery.length; i++){
            totalCost += stationery[i].getPrice();
        }
        return totalCost;
    }


    //having doubts about necessity of this two methods

    public void addStationery(Stationery[] addition) {
        int toAdd = addition.length;
        if (this.stationery == null) {
            size = toAdd;
            this.stationery = new Stationery[size];
            stationery = Arrays.copyOf(addition, size);
        } else {
            size += toAdd;
            stationery = Arrays.copyOf(stationery, size);
            System.arraycopy(addition, 0, stationery, size - toAdd, toAdd);
        }
    }

    public void addStationery(Stationery addition) {
        if (this.stationery == null) {
            size++;
            this.stationery = new Stationery[size];
            stationery[size - 1] = addition;
        } else {
            size++;
            stationery = Arrays.copyOf(stationery, size);
            stationery[size - 1] = addition;
        }
    }
}
