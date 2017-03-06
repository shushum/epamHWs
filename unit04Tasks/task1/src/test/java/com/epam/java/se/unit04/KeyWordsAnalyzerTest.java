package com.epam.java.se.unit04;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 06.03.2017.
 */
public class KeyWordsAnalyzerTest {
    @Test(expected = FileNotFoundException.class)
    public void createKeyWordsMapFromFile() throws Exception {
        KeyWordsAnalyzer test = new KeyWordsAnalyzer();
        test.analyzeFileAndWriteResults("Quiz.jva", "keyWords.txt", "result.txt");
    }

    /*@Test
    public void createKeyWordsMapFromFile() throws Exception {
        KeyWordsAnalyzer test = new KeyWordsAnalyzer();
        test.analyzeFileAndWriteResults("Quiz.java", "keyWords.txt", "result.txt");
    }
    @Test
    public void createKeyWordsMapFromFile() throws Exception {
        KeyWordsAnalyzer test = new KeyWordsAnalyzer();
        test.analyzeFileAndWriteResults("Quiz.java", "keyWords.txt", "result.txt");
    }
    @Test
    public void createKeyWordsMapFromFile() throws Exception {
        KeyWordsAnalyzer test = new KeyWordsAnalyzer();
        test.analyzeFileAndWriteResults("Quiz.java", "keyWords.txt", "result.txt");
    }*/

}