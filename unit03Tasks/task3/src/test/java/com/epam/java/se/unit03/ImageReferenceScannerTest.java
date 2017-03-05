package com.epam.java.se.unit03;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 05.03.2017.
 */
public class ImageReferenceScannerTest {

    @Test(expected = NullPointerException.class)
    public void initializeScannerWithNullFileName() throws Exception {
        ImageReferenceScanner scanner = new ImageReferenceScanner(null);
        scanner.readFileBody();
    }


    @Test(expected = FileNotFoundException.class)
    public void initializeScannerWithNotExistingFile() throws Exception {
        ImageReferenceScanner scanner =
                new ImageReferenceScanner("E://Study//java//Projects//unit03Tasks//wrongname.html");
        scanner.readFileBody();
    }

    @Test
    public void initializeScannerWithExistingFile() throws Exception {
        ImageReferenceScanner scanner =
                new ImageReferenceScanner("Java.SE.03.Information handling_task_attachment.html");

        assertFalse(scanner.readFileBody().isEmpty());
    }

    @Test
    public void extractSentencesWithImageRefs() throws Exception {

    }

}