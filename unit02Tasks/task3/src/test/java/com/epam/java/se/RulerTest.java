package com.epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 25.02.2017.
 */
public class RulerTest {
    @Test
    public void toStringTest() throws Exception {
        Ruler ruler = new Ruler(31,20);

        assertTrue(ruler.toString().contains("Price:31"));
        assertTrue(ruler.toString().contains("Length:20"));

        assertFalse(ruler.toString().contains("Colour:PINK"));
    }

}