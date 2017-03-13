package com.epam.java.se.unit05;

import java.io.*;
import java.nio.file.*;

/**
 * Created by Yegor on 12.03.2017.
 */

/**
 * A class designed to operate with .txt files, while browsing with {@link FolderBrowser}.
 */
public class TextFilesManager {
    private final String filenameExtension = ".txt";

    /**
     * Creates new .txt file within required directory.
     * @param directory path to required directory.
     * @param fileName name of .txt file to be created. Preferably without filename extension.
     * @throws FileAlreadyExistsException if .txt file with such name is already exists in required directory.
     * @throws IOException if an I/O error occurred.
     */
    public void createNewFile(File directory, String fileName) throws IOException {
        File newFile = new File(directory, fileName + filenameExtension);

        if (!newFile.createNewFile()) {
            throw new java.nio.file.FileAlreadyExistsException(String.format("File <%s> already exists in current directory.",
                    newFile.getName()));
        }
    }

    /**
     * Deletes required .txt file from required directory.
     * @param directory path to required directory.
     * @param fileName name of required .txt file to be deleted.
     * @throws InvalidActionException if there was an attempt to delete directory with name *.txt instead of .txt file.
     * @throws FileNotFoundException if required .txt file does not exist in required directory.
     */
    public void deleteFile(File directory, String fileName) throws InvalidActionException, FileNotFoundException {
        File file = new File(directory, fileName + filenameExtension);

        if (file.isDirectory()) {
            throw new InvalidActionException("You are not allowed to delete directories.");
        }

        if (!file.delete()) {
            throw new FileNotFoundException(String.format("File <%s> does not exist in current directory.", file.getName()));
        }
    }

    /**
     * Writes line of text directly into required .txt file.
     * @param directory path to directory with required .txt file.
     * @param fileName name if required .txt file.
     * @param append defines option of adding text or rewriting it.
     * @param text line of text to be written in .txt file.
     * @throws IOException
     */
    public void writeToFile(File directory, String fileName, boolean append, String text) throws IOException {
        File file = new File(directory, fileName + filenameExtension);

        if (!file.exists()){
            throw new FileNotFoundException(String.format("File <%s> does not exist in current directory.", file.getName()));
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file, append));
        writer.write(text);
        writer.flush();
        writer.close();
    }
}
