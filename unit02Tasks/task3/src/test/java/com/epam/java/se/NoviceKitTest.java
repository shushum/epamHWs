package com.epam.java.se;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Yegor on 25.02.2017.
 */
public class NoviceKitTest {
    @Test
    public void createNoviceKitAndListIt() throws Exception {
        NoviceKit john = new NoviceKit();

        String listOfNoviceKit = john.accountingOfInventory();

        assertTrue(listOfNoviceKit.contains("BallPen"));
        assertTrue(listOfNoviceKit.contains("Price:20, Colour:BLACK"));
        assertTrue(listOfNoviceKit.contains("Price:15, Colour:BLUE"));

        assertTrue(listOfNoviceKit.contains("Liner"));
        assertTrue(listOfNoviceKit.contains("Price:30, Colour:YELLOW"));

        assertTrue(listOfNoviceKit.contains("Ruler"));
        assertTrue(listOfNoviceKit.contains("Price:15, Length:25"));

        assertFalse(listOfNoviceKit.contains("PINK"));
    }

    @Test
    public void checkSizeOfNoviceKit() throws Exception {
        NoviceKit john = new NoviceKit();

        assertTrue(john.getInventory().size() == 4);
    }
}