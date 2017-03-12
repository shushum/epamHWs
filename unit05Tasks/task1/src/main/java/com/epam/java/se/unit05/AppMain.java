package com.epam.java.se.unit05;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Yegor on 12.03.2017.
 */
public class AppMain {

    public static void main(String[] args) {
        TextFilesManager filesManager = new TextFilesManager();
        Scanner scanner = new Scanner(System.in);
        boolean incorrectInput = true;

        System.out.println("Please, type in a starting directory. For default directory type 'ENTER':");

        while (incorrectInput) {
            String homeDir = scanner.nextLine();

            try {
                if (homeDir.equals("")) {
                    FolderBrowser browser = new FolderBrowser();
                } else {
                    FolderBrowser browser = new FolderBrowser(homeDir);
                }
                incorrectInput = false;
            } catch (FileNotFoundException e) {
                System.out.println("Directory " + e.getMessage() + " Please, try again.");
            }
        }

        while (true){
            
        }
    }
}
