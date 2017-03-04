package com.epam.java.se.unit03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Yegor on 04.03.2017.
 */
public class ImageReferenceScanner {
    private final static Pattern imageRef =
            Pattern.compile("(\\([Рр]ис\\. \\d+.*\\))|( [Рр]исун\\D+ \\d+)");

    private String fileName;

    public ImageReferenceScanner(String fileName){
        this.fileName = fileName;
    }

    public List<String> readFileBody(){
        List<String> file = new ArrayList<>();
        String current;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            do {
                current = reader.readLine();
            }
            while (!current.equals("<body>"));

            while ((current = reader.readLine()) != null){
                file.add(current);
            }
        } catch (IOException e){
            e.getMessage();
        }

        return file;
    }

    public static void main(String[] args) {
        ImageReferenceScanner test = new ImageReferenceScanner(
                "E://Study//java//Projects//unit03Tasks//Java.SE.03.Information handling_task_attachment.html");

        List<String> text = test.readFileBody();
        System.out.println(text.get(0));
    }
}
