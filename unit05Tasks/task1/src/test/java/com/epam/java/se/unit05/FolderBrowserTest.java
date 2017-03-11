package com.epam.java.se.unit05;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 11.03.2017.
 */
public class FolderBrowserTest {
    @Test
    public void showCurrentDirectory() throws Exception {
        FolderBrowser browser = new FolderBrowser();

        System.out.println(browser.currentDirectory());

        browser.directoryContent().forEach(System.out::println);

        browser.upToParent();
        browser.upToParent();
        browser.upToParent();

        System.out.println(browser.currentDirectory());

        browser.downToChild("Yegor");

        System.out.println(browser.currentDirectory());

        browser.downToChild("ntuser.ini");

        System.out.println(browser.currentDirectory());



    }

}