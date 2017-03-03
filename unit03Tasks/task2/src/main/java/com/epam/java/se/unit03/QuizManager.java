package com.epam.java.se.unit03;


import java.util.Locale;
import java.util.MissingResourceException;

import java.util.Scanner;

/**
 * Created by Yegor on 03.03.2017.
 */

/**
 * Allows to participate in Quiz.
 */
public class QuizManager {

    /**
     * Reads all participator's responds.
     */
    private static Scanner reader = new Scanner(System.in);

    /**
     * Quiz starts here. It is looped.
     */
    public static void main(String[] args) {
        passTheQuiz(isAcceptableLocale());
    }

    private static void passTheQuiz(String typedLocale) {

        Quiz quiz = new Quiz(new Locale(typedLocale));

        System.out.println(quiz.listOfAllQuestions());

        getAnswerOrExit(quiz);
    }

    private static String isAcceptableLocale() {
        System.out.println("Welcome to the easiest quiz in your entire life! Please, select your language!" +
                "Type 'en' for English or 'ru' for Russian:");

        while (true) {
            String locale = reader.nextLine();

            if (locale.equals("ru") || locale.equals("en")) {
                return locale;
            }

            System.out.println("Quiz supports only two versions of language. Please, carefully type 'en' for English " +
                    "or 'ru' for Russian:");
        }
    }

    private static void getAnswerOrExit(Quiz quiz) {
        while (true) {
            try {
                System.out.println("Type in the question number to get the correct answer. Type 'exit' to shutdown quiz:");
                String questionKey = reader.nextLine();

                if (questionKey.equals("exit")) {
                    return;
                }
                System.out.println(quiz.showAnswer(questionKey));
            } catch (MissingResourceException e) {
                System.out.println("Invalid question number!");
            }
        }
    }
}
