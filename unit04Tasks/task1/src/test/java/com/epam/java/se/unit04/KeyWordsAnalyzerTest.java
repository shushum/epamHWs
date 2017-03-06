package com.epam.java.se.unit04;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 06.03.2017.
 */
public class KeyWordsAnalyzerTest {
    @Test
    public void createKeyWordsMapFromFile() throws Exception {
        KeyWordsAnalyzer test = new KeyWordsAnalyzer();
        test.testMap("Quiz.java", "keyWords.txt");
    }

}