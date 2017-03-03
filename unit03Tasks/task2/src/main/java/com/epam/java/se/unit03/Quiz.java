package com.epam.java.se.unit03;


import java.util.*;

/**
 * Created by Yegor on 03.03.2017.
 */
public class Quiz {
    private ResourceBundle questions;
    private ResourceBundle answers;

    public Quiz(Locale chosenLocale) {
        Objects.requireNonNull(chosenLocale);

        this.questions = ResourceBundle.getBundle("questions", chosenLocale);
        this.answers = ResourceBundle.getBundle("answers", chosenLocale);
    }

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

    public String showAnswer(String key) {
        StringBuilder result = new StringBuilder();

        String answer = answers.getString("s" + key);
        result.append(key);
        result.append(": ");
        result.append(answer);
        result.append("\n");

        return result.toString();
    }

    public ResourceBundle getQuestions() {
        return questions;
    }

    public ResourceBundle getAnswers() {
        return answers;
    }

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
