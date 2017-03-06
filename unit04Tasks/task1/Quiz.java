package com.epam.java.se.unit03;


import java.util.*;

/**
 * Created by Yegor on 03.03.2017.
 */

/**
 * A class designed to create quizzes with specific set of questions. Supports russian and english
 * versions of questions.
 */
public class Quiz {
    /**
     * Resource Bundle for questions.
     */
    private ResourceBundle questions;
    /**
     * Resource Bundle for correct answers.
     */
    private ResourceBundle answers;

    /**
     * Creates the Quiz with chosen language.
     *
     * Choosing the unsupported language leads to setting russian version of Quiz.
     * @param chosenLocale Locale to set quiz with.
     */
    public Quiz(Locale chosenLocale) {
        Objects.requireNonNull(chosenLocale);

        this.questions = ResourceBundle.getBundle("questions", chosenLocale);
        this.answers = ResourceBundle.getBundle("answers", chosenLocale);
    }

    /**
     * Represents all questions of Quiz as a String.
     * @return String that contains all Quiz questions.
     */
    public String listOfAllQuestions() {
        StringBuilder list = new StringBuilder();

        for (int i = 1; i <= questions.keySet().size(); i++) {
            String question = questions.getString("s" + (i));
            list.append(i);
            list.append(": ");
            list.append(question);
            list.append("\n");
        }

        return list.toString();
    }

    /**
     * Shows correct answer to specific question.
     * @param key the number of question in Quiz.
     * @return correct answer to required question.
     */
    public String showAnswer(String key) {
        Objects.requireNonNull(key);

        StringBuilder result = new StringBuilder();

        String answer = answers.getString("s" + key);
        result.append(key);
        result.append(": ");
        result.append(answer);
        result.append("\n");

        return result.toString();
    }

    /**
     * These two getters were needed only for testing.
     */
    public ResourceBundle getQuestions() {
        return questions;
    }

    public ResourceBundle getAnswers() {
        return answers;
    }

    /**
     * Second method of representing all questions of Quiz as a String.
     * Can't figure out how to regularize all questions from Enumerator.
     * So, it's just hanging here for now.
     */
    /*public String listAllQuestions2() {
        StringBuilder list = new StringBuilder();
        Enumeration keys = questions.getKeys();

        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String question = questions.getString(key);

            list.append(key);
            list.append(": ");
            list.append(question);
            list.append("\n");
        }

        return list.toString();
    }*/
}
