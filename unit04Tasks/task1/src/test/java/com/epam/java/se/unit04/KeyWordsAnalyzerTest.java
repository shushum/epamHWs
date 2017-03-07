package com.epam.java.se.unit04;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 06.03.2017.
 */
public class KeyWordsAnalyzerTest {

    @Test(expected = FileNotFoundException.class)
    public void wrongPathKeyWordsInitialize() throws Exception {
        KeyWordsAnalyzer keyWordsAnalyzer = new KeyWordsAnalyzer("wrongPath");
    }

    @Test(expected = NullPointerException.class)
    public void nullKeyWordsInitialize() throws Exception {
        KeyWordsAnalyzer keyWordsAnalyzer = new KeyWordsAnalyzer(null);
    }

    @Test(expected = FileNotFoundException.class)
    public void wrongFileToAnalyzePath() throws Exception {
        KeyWordsAnalyzer keyWordsAnalyzer = new KeyWordsAnalyzer("keyWords.txt");
        keyWordsAnalyzer.analyzeFileAndWriteResults("wrongPath", "result.txt");
    }

    @Test(expected = FileNotFoundException.class)
    public void wrongResultPath() throws Exception {
        KeyWordsAnalyzer keyWordsAnalyzer = new KeyWordsAnalyzer("keyWords.txt");
        keyWordsAnalyzer.analyzeFileAndWriteResults("Quiz.java", "wrongPath");
    }

    @Test
    public void keyWordsReadIsRight() throws Exception {
        KeyWordsAnalyzer keyWordsAnalyzer = new KeyWordsAnalyzer("keyWords.txt");

        assertTrue(keyWordsAnalyzer.getKeywords().contains("abstract"));
        assertFalse(keyWordsAnalyzer.getKeywords().get(10).equals("goto"));
        assertTrue(keyWordsAnalyzer.getKeywords().contains("while"));
    }

    @Test
    public void textFromFileReadCorrectly() throws Exception {
        KeyWordsAnalyzer keyWordsAnalyzer = new KeyWordsAnalyzer("keyWords.txt");
        keyWordsAnalyzer.analyzeFileAndWriteResults("Quiz.java", "result.txt");

        assertTrue(keyWordsAnalyzer.getCodeText().contains("package"));
        assertTrue(keyWordsAnalyzer.getCodeText().contains("import"));
        assertTrue(keyWordsAnalyzer.getCodeText().contains("public"));

        assertFalse(keyWordsAnalyzer.getCodeText().contains("Не может такого быть"));
        assertFalse(keyWordsAnalyzer.getCodeText().contains("goto"));
    }

    @Test
    public void textFromFileAnalyzedCorrectly() throws Exception {
        KeyWordsAnalyzer keyWordsAnalyzer = new KeyWordsAnalyzer("keyWords.txt");
        keyWordsAnalyzer.analyzeFileAndWriteResults("Quiz.java", "result.txt");

        assertTrue(keyWordsAnalyzer.getMatches().size() <= keyWordsAnalyzer.getKeywords().size());

        assertTrue(keyWordsAnalyzer.getMatches().containsKey("package"));
        assertTrue(keyWordsAnalyzer.getMatches().containsKey("private"));
        assertTrue(keyWordsAnalyzer.getMatches().containsValue(1));

        assertFalse(keyWordsAnalyzer.getMatches().containsKey("goto"));
    }
}