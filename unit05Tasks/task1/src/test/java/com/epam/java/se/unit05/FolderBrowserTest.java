package com.epam.java.se.unit05;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 11.03.2017.
 */
public class FolderBrowserTest {

    @Test(expected = FileNotFoundException.class)
    public void folderBrowserInitWithNotExistingFile() throws Exception {
        FolderBrowser browser = new FolderBrowser("NotExistingFile");
    }

    @Test(expected = FileNotFoundException.class)
    public void changeDirectoryWithNotExistingFile() throws Exception {
        FolderBrowser browser = new FolderBrowser();
        browser.changeDirectory("NotExistingFile");
    }

    @Test
    public void changeDirectoryWithExistingFile() throws Exception {
        FolderBrowser browser = new FolderBrowser();
        browser.changeDirectory("E:\\Study\\java");

        assertTrue(browser.getPathname().getPath().equals("E:\\Study\\java"));
    }

    @Test
    public void currentDirectoryWorksRight() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java");

        assertTrue(browser.currentDirectory().equals("E:\\Study\\java>"));
    }

    @Test
    public void directoryContentWorksCorrectlyWithCorrectState() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java");

        List<String> content = browser.directoryContent();

        assertTrue(content.size() == 7);
        assertTrue(content.get(0).equals("Directory:\tBooks and stuff"));
        assertTrue(content.get(1).equals("Directory:\tClassWork"));
        assertTrue(content.get(3).equals("Directory:\tgitStudy"));
        assertTrue(content.get(4).equals("Directory:\tModules"));
    }

    @Test
    public void directoryContentWorksCorrectlyWithEmptyDirectory() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java\\emptyDirectory");

        List<String> content = browser.directoryContent();

        assertTrue(content.size() == 1);
        assertTrue(content.get(0).equals("This directory is empty."));
    }

    @Test(expected = NullPointerException.class)
    public void directoryContentWorksCorrectlyWithNotADirectory() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java\\gitStudy\\game_new.js");

        browser.directoryContent();
    }

    @Test
    public void upToParentWorksRight() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java");

        browser.upToParent();
        assertTrue(browser.currentDirectory().equals("E:\\Study>"));
    }

    @Test(expected = InvalidActionException.class)
    public void upToParentWorksRightWithRootDirectory() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\");

        browser.upToParent();
    }

    @Test
    public void downToChildWorksRight() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java");

        browser.downToChild("Projects");
        assertTrue(browser.currentDirectory().equals("E:\\Study\\java\\Projects>"));
    }

    @Test(expected = FileNotFoundException.class)
    public void downToChildWorksRightWithNotExistingChild() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java");

        browser.downToChild("Bindings of Isaac");
    }

    @Test(expected = FileNotFoundException.class)
    public void downToChildWorksRightWithNotADirectory() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java\\gitStudy\\game_new.js");

        browser.downToChild("Bindings of Isaac");
    }
}