package com.epam.java.se.unit05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor on 11.03.2017.
 */

/**
 * A class designed to browse directories and files on local computer.
 */
public class FolderBrowser {
    /**
     * Stores info about current directory.
     */
    private File pathname;

    /**
     * Creates FolderBrowser from user's home directory.
     */
    public FolderBrowser() {
        pathname = new File(System.getProperty("user.home"));
    }

    /**
     * Creates FolderBrowser from specified directory.
     * @param homeDirectory starting directory.
     * @throws FileNotFoundException if such directory does not exists.
     */
    public FolderBrowser(String homeDirectory) throws FileNotFoundException, InvalidActionException {
        fileExists(homeDirectory);

        File startingDirectory = new File(homeDirectory);

        if (startingDirectory.isFile()){
            throw new InvalidActionException("You can start browsing only from directory, not file.");
        }

        pathname = startingDirectory;
    }

    /**
     * Changes current directory to required one.
     * @param newDirectory path to required directory.
     * @throws FileNotFoundException if required directory does not exist.
     */
    public void changeDirectory(String newDirectory) throws FileNotFoundException {
        fileExists(newDirectory);

        pathname = new File(newDirectory);
    }

    /**
     * Gets the path to current directory.
     * @return String path to current directory with symbol '>' in the end.
     */
    public String currentDirectory() {
        return pathname.getPath() + ">";
    }

    /**
     * Gets names of all directories and files that are in the current directory.
     * @return {@code List<String>} of sorted names of directories and files. Sorting is made with ignoring cases.
     * @throws NullPointerException if there was an attempt to get content of a file instead of a directory.
     * Should never be happening. Logic is build around this case.
     */
    public List<String> directoryContent() throws NullPointerException {

        List<String> content = new ArrayList<>(pathname.listFiles().length);
        if (directoryIsEmpty()) {
            content.add("This directory is empty.");
        } else {
            writeIn(content);
        }
        return content;
    }

    /**
     * Moves one level up the directory tree.
     * @throws InvalidActionException if there was an attempt to move up from root directory.
     */
    public void upToParent() throws InvalidActionException {
        if (pathname.getParentFile() == null) {
            throw new InvalidActionException("Root directory.");
        }
        pathname = pathname.getParentFile();
    }

    /**
     * Moves to the required child directory of current directory.
     * @param childDirectory name of child directory.
     * @throws FileNotFoundException if required child directory does not exist.
     * @throws InvalidActionException if there was an attempt to inspect file instead of directory.
     */
    public void downToChild(String childDirectory) throws FileNotFoundException, InvalidActionException {
        File childFile = new File(pathname, childDirectory);

        fileExists(childFile.getPath());

        if (childFile.isFile()) {
            throw new InvalidActionException(String.format("Can't move to required child <%s>. It is not a directory.",
                    childDirectory));
        }

        pathname = childFile;

    }

    public File getPathname() {
        return pathname;
    }

    private void fileExists(String directory) throws FileNotFoundException {
        File file = new File(directory);

        if (!file.exists()) {
            throw new FileNotFoundException(String.format("<%s> does not exist.", directory));
        }
    }

    private List<String> writeIn(List<String> content) {
        for (File child : pathname.listFiles()) {

            if (child.isDirectory()) {
                content.add("Directory:\t" + child.getName());
            } else if (child.isFile()) {
                content.add("File:\t\t" + child.getName());
            }
        }
        content.sort(String::compareToIgnoreCase);
        return content;
    }

    private boolean directoryIsEmpty() {
        return pathname.listFiles().length == 0;
    }
}
