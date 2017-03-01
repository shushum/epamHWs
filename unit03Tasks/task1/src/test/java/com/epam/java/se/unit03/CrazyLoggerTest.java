package com.epam.java.se.unit03;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 27.02.2017.
 */
public class CrazyLoggerTest {
    @Test

    public void addNewMessageWorksCorrectly() throws Exception {
        CrazyLogger log = new CrazyLogger();

        log.addNewMessage("Log initialized!");

        assertTrue(log.toString().contains("Log initialized!;\n"));
        System.out.println(log.toString());
        System.out.println(log.getLog());
    }

    @Test
    public void addNewMessagesWorks() throws Exception {
        CrazyLogger log = new CrazyLogger();

        log.addNewMessage("Log initialized!");
        log.addNewMessage("Message1");
        log.addNewMessage("Message2");
        log.addNewMessage("Message3");

        LocalDate date = LocalDate.now();

        assertTrue(log.toString().contains(date.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"))));
        assertTrue(log.toString().contains("Log initialized!;\n"));
        assertTrue(log.toString().contains("Message1;\n"));
        assertTrue(log.toString().contains("Message2;\n"));
        assertTrue(log.toString().contains("Message3;\n"));

        System.out.println(log.toString());
        System.out.println(log.getLog());
    }

}