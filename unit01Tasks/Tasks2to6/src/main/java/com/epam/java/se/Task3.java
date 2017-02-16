package com.epam.java.se;

public class Task3 {
    public static void main(String[] args) {
        double a = 2, b = 7, h = 0.56;
        double x = a;
        double F;
        while (x < b) {
            F = Math.tan(2*x) - 3;
            System.out.println("Value of argument: " +  String.format("%(.2f", x) +
                    "       Value of function: " + F);
            x+=h;
        }
        F = Math.tan(2*b) - 3;
        System.out.println("Value of argument: " +  String.format("%(.2f", b) +
                "       Value of function: " + F);
    }
}
