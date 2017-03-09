package com.epam.java.se.unit04;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 09.03.2017.
 */
public class TextReCoderTest {

    @Test
    public void testRecoding() throws Exception {
        TextReCoder reCoder =
                new TextReCoder("input.txt", "output.txt");
        reCoder.recodeUTFfrom8To16();

        assertTrue(reCoder.getFileText().contains("Этот текст в кодировке ЮТИФИ-8."));
        assertTrue(reCoder.getFileText().contains("????????? Текст выглядит довольно глупо :)"));

        assertFalse(reCoder.getFileText().contains("А этот достаточно осмысленен. Но его нет."));
    }

    @Test(expected = FileNotFoundException.class)
    public void wrongInputName() throws Exception {
        TextReCoder reCoder =
                new TextReCoder("wrongName", "output.txt");
    }

    @Test(expected = FileNotFoundException.class)
    public void wrongOutputName() throws Exception {
        TextReCoder reCoder =
                new TextReCoder("wrongName", "wrongName");
    }
}