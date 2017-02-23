package com.epam.java.se;

import java.util.Objects;

/**
 * Created by Yegor on 23.02.2017.
 */
public class Pen {
    private int price;
    private String companyName;
    private InkColour inkColour;
    private PenType penType;

    public Pen(int price, String companyName, InkColour inkColour, PenType penType) {
        this.price = price;
        this.companyName = companyName;
        this.inkColour = inkColour;
        this.penType = penType;
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

        Pen pen = (Pen) obj;

        if (price != pen.price) {
            return false;
        }

        if (companyName != null ? !companyName.equals(pen.companyName) : pen.companyName != null) {
            return false;
        }

        if (inkColour != null ? !(inkColour == pen.inkColour) : pen.inkColour != null) {
            return false;
        }

        return penType != null ? penType == pen.penType : pen.penType == null;
    }

    @Override
    public int hashCode() {
        return price * 31 + (companyName != null ? companyName.hashCode() : 0) +
                inkColour.ordinal() + penType.ordinal();
    }

    @Override
    public String toString(){
        return this.getClass().getName() + "@" + "companyName: " + companyName +
                ", price: " + price + ", penType: " + penType + ", inkColour: " + inkColour;
    }




}
