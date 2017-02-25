package com.epam.java.se;


import java.util.Arrays;

/**
 * Created by Yegor on 25.02.2017.
 */
public class Novice {
    private String name;
    private Stationery[] inventory;

    public Novice(String name) {
        this.name = name;
    }

    public Novice(String name, Stationery[] belongings) {
        this.name = name;
        inventory = belongings;
    }

    public void giveBasicKitToNovice() {
        if (inventory == null) {
            inventory = createBasicKit();
        }

        else {
            Stationery[] addition = createBasicKit();
            int toAdd = addition.length;
            int newInventorySize = inventory.length + toAdd;

            inventory = Arrays.copyOf(inventory, newInventorySize);
            System.arraycopy(addition, 0, inventory, inventory.length - toAdd, toAdd);
        }
    }

    private Stationery[] createBasicKit() {
        Stationery[] basicKit = new Stationery[4];

        basicKit[0] = new BallPen(15, Colour.BLUE);
        basicKit[1] = new BallPen(15, Colour.BLACK);
        basicKit[2] = new Liner(30, Colour.YELLOW);
        basicKit[3] = new Ruler(15, 25);

        return basicKit;
    }

    public String accountingOfInventory(){
        if (inventory == null){
            return name + " currently has no stationery.";
        }
        StringBuilder message = new StringBuilder();
        message.append(name + "'s inventory:\n");
        for (int i = 0; i < inventory.length; i++) {
            message.append("\t\t\t\t" + inventory[i].toString() + "\n");
        }
        return message.toString();
    }
}
