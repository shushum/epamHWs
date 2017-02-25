package com.epam.java.se;

/**
 * Created by Yegor on 25.02.2017.
 */
public class Ruler extends Stationery {
    private int length;

    public Ruler(int price, int length) {
        super(price);
        if (length <= 0) {
            String error = "Rulers can't be negative length. You know that, right?";
            throw new IllegalArgumentException(error);
        }
        this.length = length;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Ruler ruler = (Ruler) obj;
        if (!super.equals(ruler)) {
            return false;
        }
        return length == ruler.length;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + length;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(super.toString());
        result.append(", Length:" + this.length);
        return result.toString();
    }

}
