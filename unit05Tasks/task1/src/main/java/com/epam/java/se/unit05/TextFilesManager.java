package com.epam.java.se.unit05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

/**
 * Created by Yegor on 12.03.2017.
 */
public class TextFilesManager {
    private final String filenameExtension = ".txt";

    public void createNewFile(File directory, String fileName) throws IOException {
        File newFile = new File(directory, fileName + filenameExtension);

        if (!newFile.createNewFile()) {
            throw new java.nio.file.FileAlreadyExistsException(String.format("File %s already exists.", newFile.getName()));
        }
    }

    public void deleteFile(File directory, String fileName) throws InvalidActionException, FileNotFoundException {
        File file = new File(directory, fileName + filenameExtension);

        if (file.isDirectory()) {
            throw new InvalidActionException("You are not allowed to delete directories");
        }

        if (!file.delete()) {
            throw new FileNotFoundException(String.format("File %s does not exist.", file.getName()));
        }
    }
}
