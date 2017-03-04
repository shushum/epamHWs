package com.epam.java.se.unit03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Yegor on 04.03.2017.
 */
public class ImageReferenceScanner {
    private final static Pattern imageRef =
            Pattern.compile("(\\([Рр]ис\\. ?\\d+((, ?\\d+)?|( и \\d+)?|((-| )[а-я](,[а-я])*)?)\\))|( [Рр]исун\\D\\D \\d+)");
    private final static Pattern number =
            Pattern.compile("\\d+");

    private String fileName;

    public ImageReferenceScanner(String fileName) {
        this.fileName = fileName;
    }

    public List<String> readFileBody() {
        List<String> file = new ArrayList<>();
        String current;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), "windows-1251"))) {

            do {
                current = reader.readLine();
            }
            while (!current.equals("<body>"));

            while ((current = reader.readLine()) != null) {
                file.add(current);
            }
        } catch (IOException e) {
            e.getMessage();
        }

        return file;
    }

    public boolean imageRefsAreConseq(List<String> file) {
        Objects.requireNonNull(file);

        int currentMaxRefNumber = 1;
        for (String line : file) {
            Matcher imageRefMatcher = imageRef.matcher(line);

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

    public static void main(String[] args) {
        ImageReferenceScanner test = new ImageReferenceScanner(
                "E://Study//java//Projects//unit03Tasks//Java.SE.03.Information handling_task_attachment.html");

        List<String> text = test.readFileBody();
        System.out.println(test.imageRefsAreConseq(text));
    }
}
