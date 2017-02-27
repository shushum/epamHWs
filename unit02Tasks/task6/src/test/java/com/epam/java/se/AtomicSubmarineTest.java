package com.epam.java.se;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 27.02.2017.
 */
public class AtomicSubmarineTest {
    @Test
    public void submarineLaunchTest() throws Exception {
        AtomicSubmarine submarine = new AtomicSubmarine();
        submarine.launch();
    }

}