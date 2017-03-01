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

        if (message.isEmpty() || message == separator){
            String result = String.format("Couldn't find. Message [%s] is empty or separator-kind.", message);
            return result;
        }

        int beginningOfCurrentLogLine = 0;
        int endingOfCurrentLogLine = 0;
        int indexOfMatching;
        StringBuilder result = new StringBuilder();

        while ((indexOfMatching = log.indexOf(message, endingOfCurrentLogLine)) != -1) {

            endingOfCurrentLogLine =
                    getEndingOfCurrentLogLineIndex(message, endingOfCurrentLogLine, indexOfMatching);

            beginningOfCurrentLogLine =
                    getBeginningOfCurrentLogLineIndex(beginningOfCurrentLogLine, indexOfMatching);

            result.append(log.substring(beginningOfCurrentLogLine, endingOfCurrentLogLine));
        }
        return result.toString();
    }

    private int getBeginningOfCurrentLogLineIndex(int beginningOfCurrentLogLineIndex, int indexOfMatching) {
        for (int i = indexOfMatching; i > beginningOfCurrentLogLineIndex; i--) {
            String curSubString = log.substring(i - separator.length(), i);
            if (curSubString.equals(separator)) {
                beginningOfCurrentLogLineIndex = i;
                break;
            }
        }
        return beginningOfCurrentLogLineIndex;
    }

    private int getEndingOfCurrentLogLineIndex(String message, int endingOfLogLineIndex, int indexOfMatching) {
        for (int i = indexOfMatching + message.length(); i < log.capacity(); i++) {
            String curSubString = log.substring(i, i + separator.length());
            if (curSubString.equals(separator)) {
                endingOfLogLineIndex = i + separator.length();
                break;
            }
        }
        return endingOfLogLineIndex;
    }

    public String toString() {
        return log.toString();
    }

    public StringBuilder getLog() {
        return log;
    }
}
