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

        browser.showCurrentDirectory();

        browser.upToParent();

        browser.showCurrentDirectory();

        browser.upToParent();

        browser.showCurrentDirectory();

        browser.upToParent();

        browser.showCurrentDirectory();


    }

}