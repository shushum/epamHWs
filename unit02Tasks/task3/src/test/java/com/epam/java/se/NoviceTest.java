package com.epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 25.02.2017.
 */
public class NoviceTest {
    @Test
    public void giveBasicKitToEmptyNovice() throws Exception {
        Novice john = new Novice("John");
        john.giveBasicKitToNovice();
        System.out.println(john.accountingOfInventory());
    }

    @Test
    public void giveBasicKitToRichNovice() throws Exception {
        Stationery[] inv = new Stationery[2];
        inv[0] = new BallPen(100, Colour.RED);
        inv[1] = new Liner(150, Colour.PINK);

        Novice john = new Novice("John", inv);
        john.giveBasicKitToNovice();
        System.out.println(john.accountingOfInventory());
    }

    @Test
    public void notGiveBasicKitToEmptyNovice() throws Exception {
        Novice john = new Novice("John");
        System.out.println(john.accountingOfInventory());
    }

    @Test
    public void notgiveBasicKitToRichNovice() throws Exception {
        Stationery[] inv = new Stationery[2];
        inv[0] = new BallPen(100, Colour.RED);
        inv[1] = new Liner(150, Colour.PINK);

        Novice john = new Novice("John", inv);
        System.out.println(john.accountingOfInventory());
    }


}