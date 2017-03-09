package com.epam.java.se.unit04;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 09.03.2017.
 */
public class TextReCoderTest {

    @Test
    public void initializeScannerWithNotExistingFile() throws Exception {
        TextReCoder reCoder =
                new TextReCoder("input.txt", "output.txt");
        reCoder.readFile();
        assertTrue(reCoder.getFileText().isEmpty());
    }
}