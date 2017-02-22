package com.epam.java.se;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IntArrayList {
    private int[] data;
    private int size;

    public IntArrayList(int[] data) {
        this.data = Arrays.copyOf(data, data.length);
        size = data.length;
    }

    public IntArrayList() {
        data = new int[10];
        size = 0;
    }

    public void add(int value) {
        ensureCapacity(size + 1);
        data[size] = value;
        size += 1;
    }

    public int get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        return data[i];
    }

    public int getSize() {
        return size;
    }

    public int maxValueInefficient() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return maxValueRec(data, 0, size);
    }

    private int maxValueRec(int[] data, int startInclusive, int endExlusive) {
        final int length = endExlusive - startInclusive;

        if (length == 1) {
            return data[startInclusive];
        } else if (length == 0) {
            return Integer.MIN_VALUE;
        }

        final int mid = startInclusive + length/2;
        return Math.max(
                maxValueRec(data, startInclusive, mid),
                maxValueRec(data, mid, endExlusive)
        );
    }

    public void sort(){
        mergeSortDown(data, 0, getSize(), new int[getSize()]);
    }

    private static void mergeSortDown(int[] data, int startInclusive, int endExclusive, int[] free) {
        final int length = endExclusive - startInclusive;
        if (length <= 1) {
            return;
        }

        final int mid = startInclusive + length/2;

        mergeSortDown(data, startInclusive, mid, free);
        mergeSortDown(data, mid, endExclusive, free);

        merger(data, startInclusive, mid, endExclusive, free);
    }

    private static void mergeSortUp(int[] data, int[] free){
        for (int size=1; size < data.length; size = size*2){
            for (int i=0; i < data.length; i = i + 2*size){
                merger(data, i, Math.min(i+size, data.length), Math.min(i+2*size, data.length), free);
            }
        }
    }

    private static void merger(int[] data, int startInclusive, int mid, int endExclusive, int[] free) {
        System.arraycopy(data, startInclusive, free, startInclusive, endExclusive - startInclusive);

        int i = startInclusive;
        int j = mid;
        for (int k = startInclusive; k < endExclusive; k++) {
            if (i >= mid) data[k] = free[j++];
            else if (j >= endExclusive) data[k] = free[i++];
            else if (free[i] < free[j]) data[k] = free[i++];
            else data[k] = free[j++];
        }
    }

    /**
     * Expects collection to be sorted.
     *
     * @param value value to find in collection
     * @return index of the value or -indexToInsert - 1
     */
    public int binarySearch(int value) { return recursiveIndexOf(data, 0, getSize(), value);}

    private static int recursiveIndexOf(int[] data, int startInclusive, int endExclusive, int value){
        if (startInclusive == endExclusive){
            return -startInclusive - 1;
        }
        final int length = endExclusive - startInclusive;
        final int mid = startInclusive + length/2;

        if (data[mid] == value) {
            return mid;
        } else if (data[mid] < value){
            return recursiveIndexOf(data, mid+1, endExclusive, value);
        } else {
            return recursiveIndexOf(data, startInclusive, mid, value);
        }
    }

    private static int cycleIndexOf(int[] data, int startInclusive, int endExclusive, int value){
        while (true){
            if (startInclusive == endExclusive){
                return -startInclusive - 1;
            }

            final int length = endExclusive - startInclusive;
            final int mid = startInclusive + length/2;

            if (data[mid] == value) {
                return mid;
            } else if (data[mid] < value) {
                startInclusive = mid + 1;
            } else{
                endExclusive = mid;
            }
        }
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= getCapacity()) {
            return;
        }
        final int newCapacity = Math.max(requiredCapacity, (getCapacity() * 3) / 2 + 1);
        data = Arrays.copyOf(data, newCapacity);
    }

    private int getCapacity() {
        return data.length;
    }


}