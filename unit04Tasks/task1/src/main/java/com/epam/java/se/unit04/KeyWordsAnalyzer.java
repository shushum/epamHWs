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
    private String codetext;
    private Map<String, Integer> result;
    private ArrayList<String> keywords = new ArrayList<>();

    public void createKeyWordsMapFromFile(String filename) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filename))) {
            Scanner scanWord = new Scanner(inputStream);

            while(scanWord.hasNext()){
                keywords.add(scanWord.next());
            }
            /*StringBuilder word = new StringBuilder();
            int symbol;

            while ((symbol = inputStream.read()) != -1) {
                if(symbol != 10 && symbol != 13){
                    word.append((char) symbol);
                }
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
