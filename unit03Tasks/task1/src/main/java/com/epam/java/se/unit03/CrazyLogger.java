package com.epam.java.se.unit03;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * A class designed to store your messages in log.
 *
 * <p>Each message is followed by the time that message was stored in log.
 * All messages are stored in the pattern <b>dd-MM-YYYY : hh-mm - message;</b>.
 * Each message starts from new line.</p>
 *
 * <p>Default separator for the messages is symbol <b>;</b>.
 * Separator can be changed with {@code setNewSeparator}.</p>
 *
 * <p>Messages from the log can be received through certain methods.</p>
 *
 * Created by Yegor on 27.02.2017.
 */
public class CrazyLogger {
    /**
     * The log is used for messages storage.
     */
    private StringBuilder log;

    /**
     * The separator is used for separating one message from another.
     */
    private String separator = ";";

    /**
     * The dateTimeFormatter is used to format LocalDateTime in certain pattern.
     */
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY : hh-mm");

    /**
     * The indexOfLastMessageWithDate indicates the first index of last message (with date).
     */
    private int indexOfLastMessageWithDate = 0;

    /**
     * Creates an empty CrazyLogger.
     */
    public CrazyLogger() {
        this.log = new StringBuilder();
    }

    /**
     * Adds a new message to the current CrazyLog.
     *
     * If message contains separator-like symbols, they will be changed to " ".
     * @param message the message to add.
     * @throws NullPointerException if given message is {@code null}.
     */
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

    /**
     * Returns the last message stored in log.
     *
     * @return the last message with the date of storing in log.
     * @throws NullPointerException if given message is {@code null}.
     */
    public String getLastMessage() {
        log.trimToSize();
        if (log.capacity() == 0) {
            return "This log is empty!";
        }
        return log.substring(indexOfLastMessageWithDate);
    }

    /**
     * Finds all mentions of certain {@code message} and returns all lines of log
     * that contains this message.
     *
     * @param message the message to find in log
     * @return the {@code String} consisting of lines that contains {@code message}.
     * @throws NullPointerException if {@code message} is {@code null}.
     */
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

    /**
     * Sets new type of separator for this log.
     *
     * @param separator the new separator of lines in log.
     * @throws NullPointerException if new {@code separator} is {@code null}.
     */
    public void setNewSeparator(String separator) {
        Objects.requireNonNull(separator);

        this.log = new StringBuilder(log.toString().replaceAll(this.separator, separator));
        this.separator = separator;
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
