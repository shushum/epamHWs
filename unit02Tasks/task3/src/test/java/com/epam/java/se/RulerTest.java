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
        System.out.println(ruler.toString());
    }

}