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

        AppMain.printCommandList();
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

    private static void printCommandList(){
        StringBuilder commands = new StringBuilder();
        commands.append("List of available commands. Type the required command to proceed:\n");
        commands.append("'cd'   - change current directory to required.\n");
        commands.append("'cd..' - move one level up the directory tree.\n");
        commands.append("'cd\\'  - move to required child directory.\n");
        commands.append("'dir'  - display the content of current directory.\n");
        commands.append("\n");
        commands.append("'mf'   - create new .txt file in current directory.\n");
        commands.append("'del'  - delete required .txt file.\n");
        commands.append("'edit' - edit required .txt file.\n");

        System.out.println(commands);





    }
}
