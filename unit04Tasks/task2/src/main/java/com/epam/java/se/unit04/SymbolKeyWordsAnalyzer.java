package com.epam.java.se.unit04;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class designed to find certain set of 'keywords' in text document.
 * Text document and set of 'keywords' are symbol-read from files.
 * The analysis result is the list of occurrences of each found 'keyword' in text document.
 * The result is symbol-written to a file.
 */
public class SymbolKeyWordsAnalyzer {
    /**
     * Text of a document.
     */
    private String codeText;
    /**
     * HashMap of found 'keywords' and their occurrences in document.
     */
    private HashMap<String, Integer> matches;
    /**
     * Full set of 'keywords'.
     */
    private ArrayList<String> keywords;

    /**
     * Creates a KeyWordAnalyzer with a binding to certain set of 'keywords'.
     *
     * @param keyWordsFilePath path to the file with 'keywords'.
     * @throws FileNotFoundException if file with such path doesn't exist.
     */
    public SymbolKeyWordsAnalyzer(String keyWordsFilePath) throws FileNotFoundException {
        createKeyWordsMapFromFile(keyWordsFilePath);
    }

    /**
     * Binds new set of 'keywords' to a KeyWordAnalyzer.
     *
     * @param keyWordsFilePath path to the file with new 'keywords'.
     * @throws FileNotFoundException if file with such path doesn't exist.
     */
    public void setKeyWordsFromNewFile(String keyWordsFilePath) throws FileNotFoundException {
        createKeyWordsMapFromFile(keyWordsFilePath);
    }

    /**
     * Reads the text document from file, analyzes 'keywords' occurrences in it.
     * Writes the result to a file.
     *
     * @param fileToAnalyzePath path to the file needed to analyze.
     * @param resultsFilePath   path to the file the result will be written.
     * @throws FileNotFoundException if file with such path doesn't exist.
     */
    public void analyzeFileAndWriteResults(String fileToAnalyzePath, String resultsFilePath) throws FileNotFoundException {
        fileExists(fileToAnalyzePath);
        fileExists(resultsFilePath);

        readFile(fileToAnalyzePath);

        countKeyWordsMatches();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultsFilePath))) {
            writer.write(resultToString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createKeyWordsMapFromFile(String filename) throws FileNotFoundException {
        Objects.requireNonNull(filename);
        fileExists(filename);

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            ArrayList<String> keywords = new ArrayList<>();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                keywords.add(currentLine);
            }

            this.keywords = keywords;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            StringBuilder fileText = new StringBuilder();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                fileText.append(currentLine);
            }
            codeText = fileText.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void countKeyWordsMatches() {
        HashMap<String, Integer> result = new HashMap<>();

        for (String keyWord : keywords) {
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

    /**
     * Getters were added for only testing purposes.
     *
     * @return
     */
    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public String getCodeText() {
        return codeText;
    }

    public HashMap<String, Integer> getMatches() {
        return matches;
    }
}
