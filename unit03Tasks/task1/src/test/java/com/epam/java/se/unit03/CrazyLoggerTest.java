package com.epam.java.se.unit03;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 27.02.2017.
 */
public class CrazyLoggerTest {
    @Test

    public void addNewMessageWorksCorrectly() throws Exception {
        CrazyLogger log = new CrazyLogger();

        log.addNewMessage("Log initialized!");

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

        System.out.println(log.toString());
        System.out.println(log.getLog());
    }

}