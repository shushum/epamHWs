package com.epam.java.se.unit05;

import java.io.File;
import java.io.FileNotFoundException;

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

    public void showCurrentDirectory() {
        System.out.println(pathname.getPath() + ">");
    }

    public void showDirectoryContent() {
        try {
            if (directoryIsEmpty()) {
                System.out.println("This directory is empty.");
            } else {
                printContent();
            }
        } catch (NullPointerException e) {
            System.out.println(String.format("%s does not denote a directory!", pathname));
        }
    }

    public void upToParent() {
        if (pathname.getParentFile() == null) {
            System.out.println("Root directory.");
            return;
        }
        pathname = pathname.getParentFile();
    }

    public void downToChild(String childDirectory) throws FileNotFoundException {
        File childFile = new File(pathname, childDirectory);

        fileExists(childFile.getPath());

        if (childFile.isDirectory()){
            pathname = childFile;
        } else if (childFile.isFile()){
            System.out.println("You can only inspect directories, not files.");
        }
    }

    private void fileExists(String directory) throws FileNotFoundException {
        File file = new File(directory);

        if (!file.exists()) {
            throw new FileNotFoundException(String.format("%s does not exist.", directory));
        }
    }

    //todo printSortedContent
    private void printContent() {
        for (File child : pathname.listFiles()) {
            if (child.isDirectory()) {
                System.out.println("Directory:\t" + child.getName());
            } else if (child.isFile()) {
                System.out.println("File:\t\t" + child.getName());
            }
        }
    }

    private boolean directoryIsEmpty() {
        return pathname.listFiles().length == 0;
    }
}
