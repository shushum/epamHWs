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

        List<Integer> consequence = new ArrayList<>();
        consequence.add(0);

        for (String line : file) {
            Matcher imageRefMatcher = imageRef.matcher(line);

            while (imageRefMatcher.find()) {
                Matcher numberMatcher = number.matcher(imageRefMatcher.group());

                while (numberMatcher.find()) {
                    consequence.add(Integer.valueOf(numberMatcher.group()));
                    if (consequence.get(consequence.size() - 1) < consequence.get(consequence.size() - 2)) {
                        return false;
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
