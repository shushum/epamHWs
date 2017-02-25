package com.epam.java.se;

/**
 * Created by Yegor on 24.02.2017.
 */
public class BallPen extends Stationery {
    private Colour colour;

    public BallPen(int price, Colour colour) {
        super(price);
        if (colour == null) {
            String error = "Please, choose one of existing colour.";
            throw new IllegalArgumentException(error);
        }
        this.colour = colour;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BallPen ballPen = (BallPen) obj;
        if (!super.equals(ballPen)) {
            return false;
        }
        return colour == ballPen.colour;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (colour != null ? colour.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(super.toString());
        result.append(", Colour:" + this.colour);
        return result.toString();
    }
}
