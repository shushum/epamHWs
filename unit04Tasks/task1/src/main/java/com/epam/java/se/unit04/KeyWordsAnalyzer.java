package com.epam.java.se.unit04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yegor on 06.03.2017.
 */
public class KeyWordsAnalyzer {
    private String codeText;
    private HashMap<String, Integer> result;
    private ArrayList<String> keyWords;


    private void createKeyWordsMapFromFile(String filename) {
        Objects.requireNonNull(filename);

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filename))) {
            ArrayList<String> keywords = new ArrayList<>();

            Scanner scanWord = new Scanner(inputStream);
            while (scanWord.hasNext()) {
                keywords.add(scanWord.next());
            }

            this.keyWords = keywords;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(String filename) {
        Objects.requireNonNull(filename);

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filename))) {
            StringBuilder fileText = new StringBuilder();
            int symbol;
            while ((symbol = inputStream.read()) != -1) {
                fileText.append((char) symbol);
            }
            codeText = fileText.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void analyzeFile(String filePath, String keyWordsFilePath) {
        readFile(filePath);
        createKeyWordsMapFromFile(keyWordsFilePath);
        HashMap<String, Integer> result = new HashMap<>();

        for (String keyWord : keyWords) {
            Pattern keyWordPattern = Pattern.compile(keyWord);
            Matcher keyWordMatcher = keyWordPattern.matcher(codeText);

            int matchCounter = 0;
            while (keyWordMatcher.find()) {
                matchCounter++;
            }

            if (matchCounter > 0) {
                result.put(keyWord, matchCounter);
            }
        }

        this.result = result;
    }

}
