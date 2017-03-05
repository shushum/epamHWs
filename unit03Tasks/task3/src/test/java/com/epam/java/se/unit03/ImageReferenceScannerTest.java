package com.epam.java.se.unit03;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    public void textAfterReadingBodyIsCleanFromHTML() throws Exception {
        ImageReferenceScanner scanner =
                new ImageReferenceScanner("Java.SE.03.Information handling_task_attachment.html");

        List<String> textArray = scanner.readFileBody();
        for (String sentence : textArray) {
            assertFalse(sentence.contains("<div>"));
            assertFalse(sentence.contains("</div>"));
            assertFalse(sentence.contains("<body>"));
            assertFalse(sentence.contains("</body>"));
            assertFalse(sentence.contains("<center>"));
            assertFalse(sentence.contains("&nbsp;"));
        }
    }

    @Test
    public void extractSentencesWithImageRefsWorksWithDiffSigns() throws Exception {
        ImageReferenceScanner scanner =
                new ImageReferenceScanner("Java.SE.03.Information handling_task_attachment.html");

        List<String> textArray = new ArrayList<>(3);
        textArray.add("Электроны – это элементарные коллапсары (Рис. 3).");
        textArray.add("Электроны – это элементарные коллапсары здесь нет ссылки.");
        textArray.add("Электроны – это элементарные коллапсары на рисунке 3.");
        textArray.add("Электроны – это элементарные коллапсары (рис. 3).");

        List<String> refsArray = scanner.extractSentencesWithImageRefs(textArray);

        assertTrue(refsArray.size() == 3);
        assertTrue(textArray.get(0).equals(refsArray.get(0)));
        assertTrue(textArray.get(2).equals(refsArray.get(1)));
        assertTrue(textArray.get(3).equals(refsArray.get(2)));
    }

    @Test
    public void extractSentencesWithImageRefsWorksWithMultiSigns() throws Exception {
        ImageReferenceScanner scanner =
                new ImageReferenceScanner("Java.SE.03.Information handling_task_attachment.html");

        List<String> textArray = new ArrayList<>(3);
        textArray.add("Электроны – это элементарные коллапсары (Рис. 3,4).");
        textArray.add("Электроны – это элементарные коллапсары (Рис. 3, 4).");
        textArray.add("Электроны – это элементарные коллапсары (Рис. 3 и 4).");
        textArray.add("Электроны – это элементарные коллапсары (Рис. 3-а).");
        textArray.add("Электроны – это элементарные коллапсары (Рис. 3 а).");
        textArray.add("Электроны – это элементарные коллапсары (Рис. 3-а,б).");

        List<String> refsArray = scanner.extractSentencesWithImageRefs(textArray);

        assertTrue(refsArray.size() == textArray.size());
        for (int i=0; i<textArray.size(); i++){
            assertTrue(refsArray.get(i).equals(textArray.get(i)));
        }
    }

    @Test
    public void imageRefsAreConsistentWorksWithOneRef() throws Exception {
        ImageReferenceScanner scanner =
                new ImageReferenceScanner("Java.SE.03.Information handling_task_attachment.html");

        List<String> textArray = new ArrayList<>(3);
        textArray.add("Электроны – это элементарные коллапсары (Рис. 1) случайное продолжение.");

        assertTrue(scanner.imageRefsAreConseq(textArray));
    }

    @Test
    public void imageRefsAreConsistentWorksWithMoreThenOneRef() throws Exception {
        ImageReferenceScanner scanner =
                new ImageReferenceScanner("Java.SE.03.Information handling_task_attachment.html");

        List<String> consistent = new ArrayList<>(3);
        consistent.add("Электроны – это элементарные коллапсары (Рис. 1) случайное продолжение (рис. 2).");
        consistent.add("Текст без ссылок");
        consistent.add("Электроны – это элементарные коллапсары на рисунке 3).");

        assertTrue(scanner.imageRefsAreConseq(consistent));

        List<String> unConsistent = new ArrayList<>(3);
        unConsistent.add("Электроны – это элементарные коллапсары (Рис. 1) случайное продолжение (рис. 2).");
        unConsistent.add("Текст без ссылок");
        unConsistent.add("Электроны – это элементарные коллапсары на рисунке 1.");

        assertFalse(scanner.imageRefsAreConseq(unConsistent));
    }

    @Test
    public void imageRefsAreConsistentWorksWhenEquals() throws Exception {
        ImageReferenceScanner scanner =
                new ImageReferenceScanner("Java.SE.03.Information handling_task_attachment.html");

        List<String> consistent = new ArrayList<>(3);
        consistent.add("Электроны – это элементарные коллапсары (Рис. 3) случайное продолжение (рис. 3).");
        consistent.add("Текст без ссылок");
        consistent.add("Электроны – это элементарные коллапсары на рисунке 3.");

        assertTrue(scanner.imageRefsAreConseq(consistent));
    }

    @Test
    public void imageRefsAreConsistentWorksWithMultiRefs() throws Exception {
        ImageReferenceScanner scanner =
                new ImageReferenceScanner("Java.SE.03.Information handling_task_attachment.html");

        List<String> consistent = new ArrayList<>(3);
        consistent.add("Электроны – это элементарные коллапсары (Рис. 1 и 2) случайное продолжение (рис. 2, 4).");

        assertTrue(scanner.imageRefsAreConseq(consistent));

        List<String> unConsistent = new ArrayList<>(3);
        unConsistent.add("Электроны – это элементарные коллапсары (Рис. 1,2) случайное продолжение (рис. 1).");

        assertFalse(scanner.imageRefsAreConseq(unConsistent));
    }
}