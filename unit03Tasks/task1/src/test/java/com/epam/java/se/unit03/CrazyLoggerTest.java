package com.epam.java.se.unit03;

import org.junit.Test;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    }

    @Test
    public void addNewMessagesWorks() throws Exception {
        CrazyLogger log = new CrazyLogger();

        LocalDate date = LocalDate.now();

        log.addNewMessage("Log initialized!");
        log.addNewMessage("Message1");
        log.addNewMessage("Message2");
        log.addNewMessage("Message3");

        assertTrue(log.toString().contains(date.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"))));
        assertTrue(log.toString().contains("Log initialized!;\n"));
        assertTrue(log.toString().contains("Message1;\n"));
        assertTrue(log.toString().contains("Message2;\n"));
        assertTrue(log.toString().contains("Message3;\n"));

    }

    @Test
    public void getLastMessageFromEmptyLog() throws Exception {
        CrazyLogger log = new CrazyLogger();

        assertTrue(log.getLastMessage().equals("This log is empty!"));
    }

    @Test
    public void getLastMessageFromLog() throws Exception {
        CrazyLogger log = new CrazyLogger();

        LocalDateTime initStamp = LocalDateTime.now();
        log.addNewMessage("Log initialized!");

        assertTrue(log.getLastMessage().contains(initStamp.format(DateTimeFormatter.ofPattern("dd-MM-YYYY : hh-mm"))));
        assertTrue(log.getLastMessage().contains(" - Log initialized!;\n"));

        LocalDateTime mes1Stamp = LocalDateTime.now();
        log.addNewMessage("Message1");

        assertTrue(log.getLastMessage().contains(mes1Stamp.format(DateTimeFormatter.ofPattern("dd-MM-YYYY : hh-mm"))));
        assertTrue(log.getLastMessage().contains(" - Message1;\n"));



    }

}