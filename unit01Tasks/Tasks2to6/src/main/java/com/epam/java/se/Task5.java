package com.epam.java.se;

public class Task5 {

    private final int size;
    private int[][] twoDiagMatrix;

    public Task5(int size){
        this.size = size;
        twoDiagMatrix = new int[size][size];
    }

    private void fillMatrix(){
        for (int i = 0; i<size; i++){
            for (int j=0; j<size; j++){
                if (i == j||i == size-1-j) {
                    twoDiagMatrix[i][j] = 1;
                }
            }
        }
    }

    public void printMatrix(){
        fillMatrix();

        for (int i = 0; i<size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(twoDiagMatrix[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
