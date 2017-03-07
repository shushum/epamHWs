package com.epam.java.se.unit04;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yegor on 06.03.2017.
 */

/**
 * A class designed to find certain set of 'keywords' in text document.
 * Text document and set of 'keywords' are byte-read from files.
 * The analysis result is the list of occurrences of each found 'keyword' in text document.
 * The result is byte-written to a file.
 */
public class KeyWordsAnalyzer {
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
     * @throws IOException if there is a problem with reading the file.
     */
    public KeyWordsAnalyzer(String keyWordsFilePath)throws FileNotFoundException {
        createKeyWordsMapFromFile(keyWordsFilePath);
    }

    /**
     * Binds new set of 'keywords' to a KeyWordAnalyzer.
     *
     * @param keyWordsFilePath path to the file with new 'keywords'.
     * @throws IOException if there is a problem with reading the file.
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
     * @throws IOException if there is a problem with reading or writing the file.
     */
    public void analyzeFileAndWriteResults(String fileToAnalyzePath, String resultsFilePath) throws FileNotFoundException {
        fileExists(fileToAnalyzePath);
        fileExists(resultsFilePath);

        readFile(fileToAnalyzePath);

        countKeyWordsMatches();

        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(resultsFilePath))) {
            outputStream.write(resultToString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createKeyWordsMapFromFile(String filename) throws FileNotFoundException {
        Objects.requireNonNull(filename);
        fileExists(filename);

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filename))) {
            ArrayList<String> keywords = new ArrayList<>();

            Scanner scanWord = new Scanner(inputStream);
            while (scanWord.hasNext()) {
                keywords.add(scanWord.next());
            }

            this.keywords = keywords;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(String filename) {
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
