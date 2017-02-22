package com.epam.java.se;

public class Task3 {

    private static final double leftBound = 2;
    private static final double rightBound = 7;
    private static final double step = 0.56;

    private static double functionValue(double argument) {
        return Math.tan(2 * argument) - 3;
    }

    public static void main(String[] args) {
        double argument = leftBound;
        while (argument < rightBound) {
            System.out.println("Value of argument: " + String.format("%(.2f", argument) +
                    "       Value of function: " + functionValue(argument));
            argument += step;
        }
        System.out.println("Value of argument: " + String.format("%(.2f", rightBound) +
                "       Value of function: " + functionValue(rightBound));
    }
}
