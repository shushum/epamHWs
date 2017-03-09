package com.epam.java.se.unit04;

import java.io.*;

/**
 * Created by Yegor on 09.03.2017.
 */
public class TextReCoder {
    private String inputPath;
    private String outputPath;
    private String fileText;

    public TextReCoder(String inputPath, String outputPath) throws FileNotFoundException{
        fileExists(inputPath);
        fileExists(outputPath);

        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    private void readFile(){
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputPath), "UTF-8"))) {
            StringBuilder fileText = new StringBuilder();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                fileText.append(currentLine);
            }
            this.fileText = fileText.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(){
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputPath), "UTF-16"))){
            writer.write(fileText);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void fileExists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

    public String getFileText() {
        return fileText;
    }
}
