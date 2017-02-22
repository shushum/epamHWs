package com.epam.java.se;

/**
 * Created by Yegor on 10.02.2017.
 */
public class Task4 {

    private final double[] array;

    public Task4(double[] array) {
        this.array = array;
    }

    private int getLength() {
        return this.array.length;
    }

    public double findSpecifiedMax() {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < getLength() / 2; i++) {
            if (max < array[i] + array[getLength() - 1 - i]) {
                max = array[i] + array[getLength() - 1 - i];
            }
        }
        return max;
    }
}
