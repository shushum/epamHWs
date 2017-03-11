package com.epam.java.se.unit05;

import java.io.File;

/**
 * Created by Yegor on 11.03.2017.
 */
public class FolderBrowser {
    private File currentDirectory;


    public FolderBrowser() {
        currentDirectory = new File(System.getProperty("user.home"));
    }

    public FolderBrowser(String homeDirectory) {
        currentDirectory = new File(homeDirectory);
    }

    public void showCurrentDirectory() {
        System.out.println(currentDirectory.getPath() + ">");
    }

    public void showChildrens() {

    }
}
