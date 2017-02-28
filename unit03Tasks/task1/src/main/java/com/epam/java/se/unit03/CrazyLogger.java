package com.epam.java.se.unit03;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Yegor on 27.02.2017.
 */
public class CrazyLogger {
    private StringBuilder log;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY : hh-mm");


    public CrazyLogger() {
        this.log = new StringBuilder();
    }

    public void addNewMessage(String message){
        LocalDateTime timeStamp = LocalDateTime.now();

        log.append(timeStamp.format(dateTimeFormatter));
        log.append(" - ");
        log.append(message);
        log.append(";\n");
        log.trimToSize();
    }

    public String toString(){
        return log.toString();
    }

    public StringBuilder getLog() {
        return log;
    }
}