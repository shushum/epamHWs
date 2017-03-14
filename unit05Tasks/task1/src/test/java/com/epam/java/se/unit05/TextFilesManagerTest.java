package com.epam.java.se.unit05;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.StringTokenizer;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 12.03.2017.
 */
public class TextFilesManagerTest {

    @Test
    public void createNewFile() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java\\Projects\\unit05Tasks\\task1");
        TextFilesManager filesManager = new TextFilesManager();

        File testFile = new File("E:\\Study\\java\\Projects\\unit05Tasks\\task1\\testFile.txt");

        assertFalse(testFile.exists());

        filesManager.createNewFile(browser.getPathname(), "testFile");

        assertTrue(testFile.exists());
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void createAlreadyExistingFile() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java\\Projects\\unit05Tasks\\task1");
        TextFilesManager filesManager = new TextFilesManager();

        File testFile2 = new File("E:\\Study\\java\\Projects\\unit05Tasks\\task1\\testFile2.txt");

        filesManager.createNewFile(browser.getPathname(), "testFile2");

        assertTrue(testFile2.exists());

        filesManager.createNewFile(browser.getPathname(), "testFile2");
    }

    @Test(expected = InvalidActionException.class)
    public void deleteDirectory() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java\\Projects\\unit05Tasks\\task1");
        TextFilesManager filesManager = new TextFilesManager();

        File testFolder = new File("E:\\Study\\java\\Projects\\unit05Tasks\\task1\\testFolder.txt");

        assertTrue(testFolder.exists());

        filesManager.deleteFile(browser.getPathname(), "testFolder");
    }

    @Test
    public void deleteExistingFile() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java\\Projects\\unit05Tasks\\task1");
        TextFilesManager filesManager = new TextFilesManager();

        File testFile = new File("E:\\Study\\java\\Projects\\unit05Tasks\\task1\\testFile.txt");

        assertTrue(testFile.exists());

        filesManager.deleteFile(browser.getPathname(), "testFile");

        assertFalse(testFile.exists());
    }

    @Test(expected = FileNotFoundException.class)
    public void deleteNotExistingFile() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java\\Projects\\unit05Tasks\\task1");
        TextFilesManager filesManager = new TextFilesManager();

        File notExistingFile = new File("E:\\Study\\java\\Projects\\unit05Tasks\\task1\\thisNeverBeExisting");

        assertFalse(notExistingFile.exists());

        filesManager.deleteFile(browser.getPathname(), "thisNeverBeExisting");
    }

    @Test(expected = FileNotFoundException.class)
    public void writeToNotExistingFile() throws Exception {
        FolderBrowser browser = new FolderBrowser("E:\\Study\\java\\Projects\\unit05Tasks\\task1");
        TextFilesManager filesManager = new TextFilesManager();

        File notExistingFile = new File("E:\\Study\\java\\Projects\\unit05Tasks\\task1\\thisNeverBeExisting");

        assertFalse(notExistingFile.exists());

        filesManager.writeToFile(browser.getPathname(), "thisNeverBeExisting", true, "bla");
    }
}