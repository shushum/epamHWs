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

            String userInput = scanner.nextLine();
            StringTokenizer userCommand = new StringTokenizer(userInput, " ");
            String action = userCommand.nextToken();

            switch (action) {
                case "cd":
                    changeDirectory(userCommand);
                    break;

                case "cd..":
                    goUpToParent(userCommand);
                    break;

                case "cd\\":
                    goDownToChild(userCommand);
                    break;

                case "dir":
                    showDirectoryContent();
                    break;

                case "mf":
                    createNewTextFile(userCommand);
                    break;

                case "del":
                    deleteTextFile(userCommand);
                    break;

                case "edit":
                    editTextFile(userCommand);
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

    private static void editTextFile(StringTokenizer userCommand) {
        try {
            String fileName = userCommand.nextToken();

            boolean append = answerToBoolean(userCommand.nextToken()); 

            String text = getTheRestOfTheUserCommand(userCommand);

            filesManager.writeToFile(browser.getPathname(), fileName, append, text);
            System.out.println(String.format("File <%s> successfully edited.", fileName));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidActionException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("File edition failed due to IOException:" + e.getMessage());
        }
    }

    private static void deleteTextFile(StringTokenizer userCommand) {
        String fileName = getTheRestOfTheUserCommand(userCommand);

        try {
            filesManager.deleteFile(browser.getPathname(), fileName);
            System.out.println(String.format("File <%s> successfully deleted.", fileName));
        } catch (InvalidActionException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createNewTextFile(StringTokenizer userCommand) {
        String fileName = getTheRestOfTheUserCommand(userCommand);

        try {
            filesManager.createNewFile(browser.getPathname(), fileName);
            System.out.println(String.format("File <%s> successfully created.", fileName));
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

    private static void goDownToChild(StringTokenizer userCommand) {
        String childDirectory = getTheRestOfTheUserCommand(userCommand);

        try {
            browser.downToChild(childDirectory);
        } catch (FileNotFoundException e) {
            System.out.println("Directory " + e.getMessage() + " Please, try again.");
        } catch (InvalidActionException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void goUpToParent(StringTokenizer userCommand) {
        if (userCommand.hasMoreTokens()){
            System.out.println("Invalid command. Try again.");
            return;
        }
        try {
            browser.upToParent();
        } catch (InvalidActionException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void changeDirectory(StringTokenizer userCommand) {
        String newDirectory = getTheRestOfTheUserCommand(userCommand);
        try {
            browser.changeDirectory(newDirectory);
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

    private static String getTheRestOfTheUserCommand(StringTokenizer userCommand) {
        return userCommand.nextToken("").replaceFirst(" ", "");
    }

    private static void printCommandList() {
        StringBuilder commands = new StringBuilder();
        commands.append("List of available commands:\n");
        commands.append("cd required\\directory\\full\\path\\name       - change current directory to required.\n");
        commands.append("cd\\ childDirectoryName                     - move to required child directory.\n");
        commands.append("cd..                                       - move one level up the directory tree.\n");
        commands.append("dir                                        - display the content of current directory.\n");
        commands.append("\n");
        commands.append("mf newTextFileName                         - create new .txt file in current directory.\n");
        commands.append("del textFileName                           - delete required .txt file.\n");
        commands.append("edit textFileName append(Y|N) textToWrite  - edit required .txt file.\n");
        commands.append("\n");
        commands.append("help                                       - print list of available commands.\n");
        commands.append("exit                                       - end session.\n");
        commands.append("\n");
        commands.append("Type the required command to proceed:\n");

        System.out.println(commands);
    }
}
