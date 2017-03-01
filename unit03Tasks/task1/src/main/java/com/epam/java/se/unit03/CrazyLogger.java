package com.epam.java.se.unit03;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Created by Yegor on 27.02.2017.
 */
public class CrazyLogger {
    private StringBuilder log;
    private final String separator = ";";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY : hh-mm");
    private int indexOfLastMessageWithDate = 0;


    public CrazyLogger() {
        this.log = new StringBuilder();
    }

    public void addNewMessage(String message) {
        Objects.requireNonNull(message);

        if (message.contains(separator)) {
            message = message.replaceAll(separator, " ");
        }

        LocalDateTime timeStamp = LocalDateTime.now();

        log.append(timeStamp.format(dateTimeFormatter));
        log.append(" - ");
        log.append(message);
        log.append(separator);
        log.append("\n");
        log.trimToSize();
        indexOfLastMessageWithDate = log.capacity() - (message.length() + 23);
    }

    public String getLastMessage() {
        log.trimToSize();
        if (log.capacity() == 0) {
            return "This log is empty!";
        }
        return log.substring(indexOfLastMessageWithDate);
    }

    public String findMessageInLog(String message) {
        Objects.requireNonNull(message);

        int beginningLogLineIndex = 0;
        int endingOfLogLineIndex = 0;
        int indexOfMatching;
        StringBuilder result = new StringBuilder();

        while ((indexOfMatching = log.indexOf(message, endingOfLogLineIndex)) != -1) {

            for (int i = indexOfMatching + message.length(); i <= log.capacity(); i++) {
                String curSubString = log.substring(i, i + separator.length());
                if (curSubString.equals(separator)) {
                    endingOfLogLineIndex = i;
                    break;
                }
            }

            for (int i = indexOfMatching - 1; i >= beginningLogLineIndex; i--) {
                String curSubString = log.substring(i - separator.length(), i);
                if (curSubString.equals(separator)){
                    beginningLogLineIndex = i;
                    break;
                }
            }

            result.append(log.substring(beginningLogLineIndex,endingOfLogLineIndex));
        }
        return result.toString();
    }

    public String toString() {
        return log.toString();
    }

    public StringBuilder getLog() {
        return log;
    }
}
