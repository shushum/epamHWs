package com.epam.java.se.unit04;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Yegor on 06.03.2017.
 */
public class KeyWordsAnalyzer {
    private String codeText;
    private Map<String, Integer> result;
    private ArrayList<String> keywords = new ArrayList<>();


    private void createKeyWordsMapFromFile(String filename) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filename))) {
            Scanner scanWord = new Scanner(inputStream);

            while (scanWord.hasNext()) {
                keywords.add(scanWord.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile(String filename) {
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

}
