package com.epam.java.se.unit03;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.io.*;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Yegor on 04.03.2017.
 */

/**
 * A class designed for scanning a text document with a view to finding image references in it.
 *
 * Class can trim down HTML documents to a clean text.
 * The references should fit certain patterns.
 * Class allows to get a {@code List<String>} of sentences with image references only.
 * Class checks if the image references are constitute a non-decreasing sequence.
 */
public class ImageReferenceScanner {
    /**
     * A pattern to find an image references.
     */
    private final static Pattern imageRef =
            Pattern.compile("(\\([Рр]ис\\. ?\\d+((, ?\\d+)?|( и \\d+)?|((-| )[а-я](,[а-я])*)?)\\))|( [Рр]исун\\D\\D \\d+)");
    /**
     * A pattern to find number. Needed for sequence check.
     */
    private final static Pattern number =
            Pattern.compile("\\d+");
    /**
     * A name of text document.
     */
    private String fileName;

    /**
     * Creates a scanner for certain text document.
     * @param fileName
     */
    public ImageReferenceScanner(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Main needed only to accomplish unit03task3 HTML document.
     * @param args
     */
    public static void main(String[] args) {
        ImageReferenceScanner taskScanner =
                new ImageReferenceScanner("Java.SE.03.Information handling_task_attachment.html");

        try {
            List<String> text = taskScanner.readFileBody();

            if (taskScanner.imageRefsAreConseq(text)) {
                System.out.println("Image references are consistent in this text");
            } else {
                System.out.println("Image references are not consistent in this text");
            }

            List<String> sentencesWithImageRefs = taskScanner.extractSentencesWithImageRefs(text);
            System.out.println("All sentences with image references are extracted!");

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Reads a text from file. In case of HTML document trims it.
     * @return {@code List<String>} full of separate 'clean' sentences.
     * @throws FileNotFoundException if file does not exist.
     */
    public List<String> readFileBody() throws FileNotFoundException {
        List<String> file = new ArrayList<>();
        String currentLine;

        fileExists(fileName);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), "windows-1251"))) {

            do {
                currentLine = reader.readLine();
            }
            while (!currentLine.equals("<body>"));

            while ((currentLine = reader.readLine()) != null) {
                List<String> sentences = breakLineInSentences(currentLine);
                file.addAll(sentences);
            }
        } catch (IOException e) {
            e.getMessage();
        }

        return file;
    }

    /**
     * Checks if the image references in this text are constitute a non-decreasing sequence.
     * @param file is a {@code List<String>} of separate 'clean' sentences.
     * @return {@code true} when image references are not decreasing, {@code false} in other cases.
     */
    public boolean imageRefsAreConseq(List<String> file) {
        Objects.requireNonNull(file);

        int currentMaxRefNumber = 1;
        for (String sentence : file) {
            Matcher imageRefMatcher = imageRef.matcher(sentence);

            while (imageRefMatcher.find()) {
                String currentImageRef = imageRefMatcher.group();
                Matcher numberMatcher = number.matcher(currentImageRef);

                while (numberMatcher.find()) {
                    int currentRefNumber = Integer.valueOf(numberMatcher.group());

                    if (currentRefNumber < currentMaxRefNumber) {
                        return false;
                    } else if (currentRefNumber > currentMaxRefNumber) {
                        currentMaxRefNumber = currentRefNumber;
                    }
                }

            }

        }
        return true;
    }

    /**
     * Extracts from text only the sentences with image references.
     * @param file is a {@code List<String>} of separate 'clean' sentences.
     * @return {@code List<String>} of only the sentences with image references.
     */
    public List<String> extractSentencesWithImageRefs(List<String> file) {
        Objects.requireNonNull(file);

        List<String> sentencesWithRefs = new ArrayList<>();

        for (String sentence : file) {
            if (imageRef.matcher(sentence).find()) {
                sentencesWithRefs.add(sentence);
            }
        }

        return sentencesWithRefs;
    }

    private List<String> breakLineInSentences(String line) {
        List<String> sentences = new ArrayList<>();
        BreakIterator breaker = BreakIterator.getSentenceInstance();

        breaker.setText(line);

        int start = breaker.first();
        for (int end = breaker.next(); end != BreakIterator.DONE; start = end, end = breaker.next()) {
            String sentence = line.substring(start, end);

            sentence = trim(sentence);

            if (!sentence.isEmpty()) {
                sentences.add(sentence);
            }
        }

        return sentences;
    }

    private String trim(String sentence) {
        sentence = Jsoup.clean(sentence, Whitelist.simpleText());
        if (sentence.contains("&nbsp;")) {
            sentence = sentence.replaceAll("&nbsp;", "");
        }
        return sentence;
    }

    private void fileExists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }
}
