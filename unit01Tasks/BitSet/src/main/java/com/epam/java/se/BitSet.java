package com.epam.java.se;


import java.util.Arrays;

/**
 * This class stores a Set of non-negative integer values.
 */
public class BitSet {
    /**
     * Set of non-negative integer values.
     */
    private long[] data;

    /**
     * Creates a default Set of non-negative integer values.
     */
    public BitSet() {
        data = new long[1];
    }

    /**
     * Creates a specified Set of non-negative integer values.
     * @param values
     */
    public BitSet(long[] values) {
        data = new long[values.length];
        System.arraycopy(values, 0, data, 0, values.length);
    }

    /**
     * Adds a new value to the proceeded BitSet.
     *
     * @param value the value to add
     */
    public void add(int value) {
        if (value < 0) {
            return;
        }

        final int arrayElementToAddInto = value / 64;
        ensureCapacity(arrayElementToAddInto);
        data[arrayElementToAddInto] |= 1L << value;
    }

    private void ensureCapacity(int requiredCapacity) {
        if((requiredCapacity+1) < getCapacity()){
            return;
        }
        final int newCapacity = Math.max((requiredCapacity+1),(getCapacity()*3)/2 + 1);
        data = Arrays.copyOf(data, newCapacity);
    }

    private int getCapacity(){
        return data.length;
    }


    /**
     * Removes specified value from the proceeded BitSet.
     *
     * @param value the value to remove
     */
    public void remove(int value){
        if (canNotStore(value)){
            return;
        }

        final int elementToRemove = value / 64;
        data[elementToRemove] &= ~(1L << value);
    }

    /**
     * Checks the presence of specified value in the proceeded BitSet.
     *
     * @param value the value to check
     * @return the result of presence check
     */
    public boolean contains(int value){
        if (canNotStore(value)){
            return false;
        }

        final int elementToCheck = value / 64;
        final long mask = 1L << value;
        final long res = data[elementToCheck] & mask;
        return res != 0;
    }

    private boolean canNotStore(int value){
        if (value < 0 || value > getCapacity() * 64 - 1 ){
            return true;
        }
        return false;
    }

    /**
     * Creates a new BitSet, based on union of proceeded and other BitSets.
     *
     * @param other BitSet to unite proceeded with
     * @return the result of the union
     */
    public BitSet union(BitSet other){

        if (this.data.length <= other.data.length) {

            BitSet unionSet = new BitSet(other.data);
            for (int i = 0; i < this.data.length; i++) {
                unionSet.data[i] |= this.data[i];
            }
            return unionSet;
        }

        else{

            BitSet unionSet = new BitSet(this.data);
            for (int i=0; i < other.data.length; i++){
                unionSet.data[i] |= other.data[i];
            }
            return unionSet;
        }
    }

    /**
     * Creates a new BitSet, based on intersection of proceeded and other BitSets.
     *
     * @param other BitSet to intersect proceeded with
     * @return the result of the intersection
     */
    public BitSet intersection(BitSet other){

        if (this.data.length <= other.data.length) {

            BitSet intersectionSet = new BitSet(other.data);
            for (int i = 0; i < this.data.length; i++) {
                intersectionSet.data[i] = this.data[i] & other.data[i];
            }
            for (int i = this.data.length; i < other.data.length; i++) {
                intersectionSet.data[i] = 0;
            }
            return intersectionSet;
        }

        else{

            BitSet intersectionSet = new BitSet(this.data);
            for (int i=0; i < other.data.length; i++){
                intersectionSet.data[i] = this.data[i] & other.data[i];
            }
            for (int i = other.data.length; i < this.data.length; i++) {
                intersectionSet.data[i] = 0;
            }
            return intersectionSet;
        }
    }

    /**
     * Creates a new BitSet, based on difference between proceeded and other BitSets.
     *
     * It uses the "exclusive or" to calculate the difference
     * @param other BitSet to differ proceeded with
     * @return the result of the difference
     */
    public BitSet difference(BitSet other){

        if (this.data.length <= other.data.length) {

            BitSet differenceSet = new BitSet(other.data);
            for (int i = 0; i < this.data.length; i++) {
                differenceSet.data[i] = this.data[i] ^ other.data[i];
            }
            for (int i = this.data.length; i < other.data.length; i++) {
                differenceSet.data[i] = other.data[i];
            }
            return differenceSet;
        }

        else{

            BitSet differenceSet = new BitSet(this.data);
            for (int i=0; i < other.data.length; i++){
                differenceSet.data[i] = this.data[i] ^ other.data[i];
            }
            for (int i = other.data.length; i < this.data.length; i++) {
                differenceSet.data[i] = this.data[i];
            }
            return differenceSet;
        }
    }

    /**
     * Checks if proceeded BitSet is the subset of another BitSet.
     *
     * @param other BitSet to be checked on being superset for proceeded BitSet
     * @return the result of subset check
     */
    public boolean isSubsetOf(BitSet other){

        BitSet checkSet = this.intersection(other);
        for (int i=0; i < this.data.length; i++){
            if(checkSet.data[i] != this.data[i]){
                return false;
            }
        }
        return true;
    }
}
