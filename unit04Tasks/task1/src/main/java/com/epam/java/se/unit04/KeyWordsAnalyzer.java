package com.epam.java.se.unit04;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yegor on 06.03.2017.
 */
public class KeyWordsAnalyzer {
    private String codeText;
    private HashMap<String, Integer> matches;
    private ArrayList<String> keyWords;

    public void analyzeFileAndWriteResults(String fileToAnalyzePath, String keyWordsFilePath, String resultsFilePath) {
        Objects.requireNonNull(resultsFilePath);

        countKeyWordsMatches(fileToAnalyzePath, keyWordsFilePath);

        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(resultsFilePath))) {

            outputStream.write(resultToString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    private void countKeyWordsMatches(String filePath, String keyWordsFilePath) {
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

        this.matches = result;
    }

    private String resultToString() {
        StringBuilder prettyResult = new StringBuilder();

        if (matches.isEmpty()) {
            return prettyResult.append("No matches found.").toString();
        }

        matches.forEach((s, i) -> {
            prettyResult.append("Key word '").append(s).append("' has ").append(i).append(" entries.\n");
        });

        return prettyResult.toString();
    }

}
