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

    public KeyWordsAnalyzer(String keyWordsFilePath) throws IOException {
        createKeyWordsMapFromFile(keyWordsFilePath);
    }

    public void setKeyWordsFromNewFile(String keyWordsFilePath) throws IOException{
        createKeyWordsMapFromFile(keyWordsFilePath);
    }

    public void analyzeFileAndWriteResults(String fileToAnalyzePath, String resultsFilePath) throws IOException{
        fileExists(fileToAnalyzePath);
        fileExists(resultsFilePath);

        readFile(fileToAnalyzePath);

        countKeyWordsMatches();

        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(resultsFilePath))) {
            outputStream.write(resultToString().getBytes());
        }
    }

    private void createKeyWordsMapFromFile(String filename) throws IOException {
        Objects.requireNonNull(filename);

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filename))) {
            ArrayList<String> keywords = new ArrayList<>();

            Scanner scanWord = new Scanner(inputStream);
            while (scanWord.hasNext()) {
                keywords.add(scanWord.next());
            }

            this.keyWords = keywords;
        }
    }

    private void readFile(String filename) throws IOException{
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filename))) {
            StringBuilder fileText = new StringBuilder();
            int symbol;
            while ((symbol = inputStream.read()) != -1) {
                fileText.append((char) symbol);
            }
            codeText = fileText.toString();
        }
    }

    private void countKeyWordsMatches() throws IOException{
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

    private void fileExists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

    public ArrayList<String> getKeyWords() {
        return keyWords;
    }

    public String getCodeText() {
        return codeText;
    }

    public HashMap<String, Integer> getMatches() {
        return matches;
    }
}
