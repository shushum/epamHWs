package com.epam.java.se;

public class Task2 {
    public static void main(String[] args) {
        double eps = 1E-4;
        int i = 1;
        double seq = (double) 1/((i+1)*(i+1));
        while (seq > eps) {
            System.out.println("Sequence element " + seq + " with number " + i);
            i++;
            seq = (double) 1/((i+1)*(i+1));
        }
        System.out.println("Required number " + i);
    }
}
