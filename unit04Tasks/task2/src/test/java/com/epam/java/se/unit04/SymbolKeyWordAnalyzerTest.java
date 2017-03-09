package com.epam.java.se.unit04;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 09.03.2017.
 */
public class SymbolKeyWordAnalyzerTest {
    @Test(expected = FileNotFoundException.class)
    public void wrongPathKeyWordsInitialize() throws Exception {
        SymbolKeyWordsAnalyzer keyWordsAnalyzer = new SymbolKeyWordsAnalyzer("wrongPath");
    }

    @Test(expected = NullPointerException.class)
    public void nullKeyWordsInitialize() throws Exception {
        SymbolKeyWordsAnalyzer keyWordsAnalyzer = new SymbolKeyWordsAnalyzer(null);
    }

    @Test(expected = FileNotFoundException.class)
    public void wrongFileToAnalyzePath() throws Exception {
        SymbolKeyWordsAnalyzer keyWordsAnalyzer = new SymbolKeyWordsAnalyzer("keyWords.txt");
        keyWordsAnalyzer.analyzeFileAndWriteResults("wrongPath", "result.txt");
    }

    @Test(expected = FileNotFoundException.class)
    public void wrongResultPath() throws Exception {
        SymbolKeyWordsAnalyzer keyWordsAnalyzer = new SymbolKeyWordsAnalyzer("keyWords.txt");
        keyWordsAnalyzer.analyzeFileAndWriteResults("LearningGroup.java", "wrongPath");
    }

    @Test
    public void keyWordsReadIsRight() throws Exception {
        SymbolKeyWordsAnalyzer keyWordsAnalyzer = new SymbolKeyWordsAnalyzer("keyWords.txt");

        assertTrue(keyWordsAnalyzer.getKeywords().contains("abstract"));
        assertFalse(keyWordsAnalyzer.getKeywords().get(10).equals("goto"));
        assertTrue(keyWordsAnalyzer.getKeywords().contains("while"));
    }

    @Test
    public void textFromFileReadCorrectly() throws Exception {
        SymbolKeyWordsAnalyzer keyWordsAnalyzer = new SymbolKeyWordsAnalyzer("keyWords.txt");
        keyWordsAnalyzer.analyzeFileAndWriteResults("LearningGroup.java", "result.txt");

        assertTrue(keyWordsAnalyzer.getCodeText().contains("package"));
        assertTrue(keyWordsAnalyzer.getCodeText().contains("import"));
        assertTrue(keyWordsAnalyzer.getCodeText().contains("public"));

        assertFalse(keyWordsAnalyzer.getCodeText().contains("Не может такого быть"));
        assertFalse(keyWordsAnalyzer.getCodeText().contains("goto"));
    }

    @Test
    public void textFromFileAnalyzedCorrectly() throws Exception {
        SymbolKeyWordsAnalyzer keyWordsAnalyzer = new SymbolKeyWordsAnalyzer("keyWords.txt");
        keyWordsAnalyzer.analyzeFileAndWriteResults("LearningGroup.java", "result.txt");

        assertTrue(keyWordsAnalyzer.getMatches().size() <= keyWordsAnalyzer.getKeywords().size());

        assertTrue(keyWordsAnalyzer.getMatches().containsKey("package"));
        assertTrue(keyWordsAnalyzer.getMatches().containsKey("private"));
        assertTrue(keyWordsAnalyzer.getMatches().containsKey("throw"));
        assertTrue(keyWordsAnalyzer.getMatches().containsValue(7));

        assertFalse(keyWordsAnalyzer.getMatches().containsKey("goto"));
    }

}