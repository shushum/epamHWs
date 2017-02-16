package com.epam.java.se;

public class Task5 {
    public static void main(String[] args) {
        int n = 10;
        int[][] twoDiag = new int[n][n];
        for (int i = 0; i<n; i++){
            for (int j=0; j<n; j++){
                if (i == j||i == n-1-j) {
                    twoDiag[i][j] = 1;
                }
            }
        }

        for (int i = 0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(twoDiag[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
