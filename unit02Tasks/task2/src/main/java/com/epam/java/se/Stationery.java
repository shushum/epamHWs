package com.epam.java.se;

/**
 * Created by Yegor on 24.02.2017.
 */
public class Stationery {
    private int price;

    private static int idGlobal = 0;
    private final int id;

    public Stationery() {
        idGlobal++;
        id = idGlobal;
    }

    public Stationery(int price) {
        this();
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (null == obj) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        Stationery stationery = (Stationery) obj;

        if (this.id != stationery.id) {
            return false;
        }

        return this.price == stationery.price;
    }

    @Override
    public int hashCode() {
        return 31 * price + id;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(this.getClass().getSimpleName() + "@ ");
        result.append("ID: " + this.id);
        result.append(", Price:" + this.price);
        return result.toString();
    }
}
