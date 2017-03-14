package com.epam.java.se.unit05;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Yegor on 12.03.2017.
 */

/**
 * Main loop of app. Giant and clunky. Should it be this way? Dunno:(
 */
public class AppMain {
    private static FolderBrowser browser = null;
    private final static TextFilesManager filesManager = new TextFilesManager();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean incorrectInput = true;

        while (incorrectInput) {
            System.out.println("Please, type in a starting directory. For default directory press 'ENTER':");
            String homeDir = scanner.nextLine();

            try {
                if (homeDir.equals("")) {
                    browser = new FolderBrowser();
                } else {
                    browser = new FolderBrowser(homeDir);
                }
                incorrectInput = false;
            } catch (FileNotFoundException e) {
                System.out.println("Directory " + e.getMessage() + " Please, try again.");
            } catch (InvalidActionException e) {
                System.out.println(e.getMessage());
            }
        }

        printCommandList();

        while (true) {
            System.out.println(browser.currentDirectory());

            String command = scanner.nextLine();

            switch (command) {
                case "cd":
                    changeDirectory(scanner);
                    break;

                case "cd..":
                    goUpToParent();
                    break;

                case "cd\\":
                    goDownToChild();
                    break;

                case "dir":
                    showDirectoryContent();
                    break;

                case "mf":
                    createNewTextFile();
                    break;

                case "del":
                    deleteTextFile();
                    break;

                case "edit":
                    editTextFile();
                    break;

                case "help":
                    printCommandList();
                    break;

                case "exit":
                    System.exit(0);

                default:
                    System.out.println("Invalid command. Try again.");
                    break;
            }
        }
    }

    private static void editTextFile() {
        try {
            System.out.println("Rewrite file from beginning? Y|N:");
            boolean answer = answerToBoolean(scanner.nextLine());

            System.out.println("Type line for writing in file:");
            String text = scanner.nextLine();

            System.out.println("Type name of .txt file to be edited:");
            String editedFile = scanner.nextLine();

            filesManager.writeToFile(browser.getPathname(), editedFile, answer, text);
            System.out.println(String.format("File <%s> successfully edited.", editedFile));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidActionException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("File edition failed due to IOException:" + e.getMessage());
        }
    }

    private static void deleteTextFile() {
        System.out.println("Type name of .txt file to be deleted:");
        String file = scanner.nextLine();

        try {
            filesManager.deleteFile(browser.getPathname(), file);
            System.out.println(String.format("File <%s> successfully deleted.", file));
        } catch (InvalidActionException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createNewTextFile() {
        System.out.println("Type name of .txt file to be created:");
        String newFile = scanner.nextLine();

        try {
            filesManager.createNewFile(browser.getPathname(), newFile);
            System.out.println(String.format("File <%s> successfully created.", newFile));
        } catch (FileAlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("File creation failed due to IOException:" + e.getMessage());
        }
    }

    private static void showDirectoryContent() {
        try {
            browser.directoryContent().forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.println("You tried to list content of a file, not folder. You shouldn't even be here! " +
                    "Dirty hacka!");
        }
    }

    private static void goDownToChild() {
        System.out.println("Type name of required child:");
        String child = scanner.nextLine();

        try {
            browser.downToChild(child);
        } catch (FileNotFoundException e) {
            System.out.println("Directory " + e.getMessage() + " Please, try again.");
        } catch (InvalidActionException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void goUpToParent() {
        try {
            browser.upToParent();
        } catch (InvalidActionException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void changeDirectory(Scanner scanner) {
        System.out.println("Type path to required directory:");
        String dir = scanner.nextLine();
        try {
            browser.changeDirectory(dir);
        } catch (FileNotFoundException e) {
            System.out.println("Directory " + e.getMessage() + " Please, try again.");
        }
    }

    private static boolean answerToBoolean(String s) throws InvalidActionException {
        switch (s.toUpperCase()) {
            case "Y":
                return false;
            case "N":
                return true;
            default:
                throw new InvalidActionException("Invalid answer type. Try Y for 'yes' or N for 'no'.");
        }
    }

    private static void printCommandList() {
        StringBuilder commands = new StringBuilder();
        commands.append("List of available commands:\n");
        commands.append("cd   - change current directory to required.\n");
        commands.append("cd.. - move one level up the directory tree.\n");
        commands.append("cd\\  - move to required child directory.\n");
        commands.append("dir  - display the content of current directory.\n");
        commands.append("\n");
        commands.append("mf   - create new .txt file in current directory.\n");
        commands.append("del  - delete required .txt file.\n");
        commands.append("edit - edit required .txt file.\n");
        commands.append("\n");
        commands.append("help - print list of available commands.\n");
        commands.append("exit - end session.\n");
        commands.append("\n");
        commands.append("Type the required command to proceed:\n");

        System.out.println(commands);


    }
}
