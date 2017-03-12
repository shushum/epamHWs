package com.epam.java.se.unit05;

import java.io.*;
import java.nio.file.*;

/**
 * Created by Yegor on 12.03.2017.
 */
public class TextFilesManager {
    private final String filenameExtension = ".txt";

    public void createNewFile(File directory, String fileName) throws IOException {
        File newFile = new File(directory, fileName + filenameExtension);

        if (!newFile.createNewFile()) {
            throw new java.nio.file.FileAlreadyExistsException(String.format("File %s already exists in current directory.",
                    newFile.getName()));
        }
    }

    public void deleteFile(File directory, String fileName) throws InvalidActionException, FileNotFoundException {
        File file = new File(directory, fileName + filenameExtension);

        if (file.isDirectory()) {
            throw new InvalidActionException("You are not allowed to delete directories");
        }

        if (!file.delete()) {
            throw new FileNotFoundException(String.format("File %s does not exist in current directory.", file.getName()));
        }
    }

    public void writeToFile(File directory, String fileName, boolean append, String text) throws IOException {
        File file = new File(directory, fileName + filenameExtension);

        if (!file.exists()){
            throw new FileNotFoundException(String.format("File %s does not exist in current directory.", file.getName()));
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file, append));
        writer.write(text);
        writer.flush();
        writer.close();
    }
}
