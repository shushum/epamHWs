package com.epam.java.se.unit05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yegor on 11.03.2017.
 */
public class FolderBrowser {
    private File pathname;

    public FolderBrowser() {
        pathname = new File(System.getProperty("user.home"));
    }

    public FolderBrowser(String homeDirectory) throws FileNotFoundException {
        fileExists(homeDirectory);

        pathname = new File(homeDirectory);
    }

    public void changeDirectory(String newDirectory) throws FileNotFoundException {
        fileExists(newDirectory);

        pathname = new File(newDirectory);
    }

    public String currentDirectory() {
        return pathname.getPath() + ">";
    }

    public List<String> directoryContent() throws NullPointerException {

        List<String> content = new ArrayList<>(pathname.listFiles().length);
        if (directoryIsEmpty()) {
            content.add("This directory is empty.");
        } else {
            writeIn(content);
        }
        return content;
    }

    public void upToParent() throws InvalidActionException {
        if (pathname.getParentFile() == null) {
            throw new InvalidActionException("Root directory.");
        }
        pathname = pathname.getParentFile();
    }

    public void downToChild(String childDirectory) throws FileNotFoundException, InvalidActionException {
        File childFile = new File(pathname, childDirectory);

        fileExists(childFile.getPath());

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
