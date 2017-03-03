package com.epam.java.se.unit03;


import java.util.*;

/**
 * Created by Yegor on 03.03.2017.
 */
public class Quiz {
    private ResourceBundle questions;
    private ResourceBundle answers;

    public Quiz(Locale chosenLocale) {
        this.questions = ResourceBundle.getBundle("questions", chosenLocale);
        this.answers = ResourceBundle.getBundle("answers", chosenLocale);
    }

    public String listAllQuestions() {
        StringBuilder list = new StringBuilder();

        for (int i = 0; i < questions.keySet().size(); i++) {
            String question = questions.getString("s" + (i + 1));
            list.append("Question ");
            list.append(i + 1);
            list.append(": ");
            list.append(question);
            list.append("\n");
        }

        return list.toString();
    }

    public String listAllQuestions2() {
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
    }

    private void isAcceptableLocale(Locale locale) {
        Objects.requireNonNull(locale);

        while (true) {
            if (locale.getLanguage().equals("ru") || locale.getLanguage().equals("en")) {
                break;
            }
            System.out.println("Quiz supports only two versions of language. Please, type 'en' for English or " +
                    "'ru' for Russian:");
        }
    }

    public static void main(String[] args) {
        Locale locale = new Locale("en");
        Quiz test = new Quiz(locale);



        System.out.println(test.listAllQuestions());



    }

}
