package com.epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 25.02.2017.
 */
public class LinerTest {
    @Test
    public void toStringTest() throws Exception {
        Liner liner = new Liner(31,Colour.YELLOW);

        assertTrue(liner.toString().contains("Price:31"));
        assertTrue(liner.toString().contains("Colour:YELLOW"));

        assertFalse(liner.toString().contains("Colour:PINK"));
    }

}