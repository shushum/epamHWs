package com.epam.java.se;

/**
 * Created by Yegor on 10.02.2017.
 */
public class Task4 {
    public static void main(String[] args) {
        int n = 10;
        double[] array = new double[n];
        double max = array[0]+array[n-1];

        for(int i=1; i<n/2; i++){
            if (max < array[i]+array[n-1-i]){
                max = array[i]+array[n-1-i];
            }
        }
        System.out.println(max);
    }
}
