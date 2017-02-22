package com.epam.java.se;

public class Task2 {

    private static final double eps = 1E-4;

    private static double seqValue(int seqElement) {
        return 1 / Math.pow(seqElement + 1, 2);
    }

    public static void main(String[] args) {
        int i = 1;

        while (seqValue(i) > eps) {
            System.out.println("Sequence element " + i + " with value " + seqValue(i));
            i++;
        }
        System.out.println("Required number " + i);
    }
}
