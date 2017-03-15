package com.epam.java.se.unit07;

/**
 * Created by Yegor on 15.03.2017.
 */
public class Operation {
    private final String from;
    private final String to;
    private final long amount;

    public Operation(String from, String to, long amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
