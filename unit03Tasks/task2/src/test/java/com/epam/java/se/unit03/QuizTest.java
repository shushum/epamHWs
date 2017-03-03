package com.epam.java.se.unit03;

import org.junit.Test;

import java.util.Locale;
import java.util.MissingResourceException;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 04.03.2017.
 */
public class QuizTest {
    @Test
    public void initializeEnQuiz() throws Exception {
        Locale locale = new Locale("en");
        Quiz quiz = new Quiz(locale);

        assertTrue(quiz.getQuestions().getString("s4").equals("How many months have 28 days?"));
        assertTrue(quiz.getAnswers().getString("s7").equals("42"));

    }

    @Test
    public void initializeRuQuiz() throws Exception {
        Locale locale = new Locale("ru");
        Quiz quiz = new Quiz(locale);

        assertTrue(quiz.getQuestions().getString("s3").equals("2+2?"));
        assertTrue(quiz.getAnswers().getString("s6").equals("Не ешь, подумой!"));
    }

    @Test
    public void initializeUnsupportedLocaleQuizAkaDefault() throws Exception {
        Locale locale = new Locale("fr");
        Quiz quiz = new Quiz(locale);

        assertTrue(quiz.getQuestions().getString("s8").equals("Это djent?"));
    }

    @Test(expected = NullPointerException.class)
    public void initializeNullLocaleQuiz() throws Exception {
        Quiz quiz = new Quiz(null);

        assertTrue(quiz.getQuestions().getString("s8").equals("Это djent?"));
    }

    @Test
    public void listOfAllQuestions() throws Exception {
        Locale locale = new Locale("en");
        Quiz quiz = new Quiz(locale);

        assertTrue(quiz.listOfAllQuestions().contains("6: Cat or bread?\n"));
        assertTrue(quiz.listOfAllQuestions().contains("1: How many days in a week?\n"));

    }

    @Test
    public void showAnswerOfExistingQuestion() throws Exception {
        Locale locale = new Locale("en");
        Quiz quiz = new Quiz(locale);

        assertTrue(quiz.showAnswer("5").contains("5: Wrong\n"));
        assertTrue(quiz.showAnswer("2").contains("2: Tuesday\n"));

    }

    @Test(expected = MissingResourceException.class)
    public void showAnswerOfNotExistingQuestion() throws Exception {
        Locale locale = new Locale("en");
        Quiz quiz = new Quiz(locale);

        assertTrue(quiz.showAnswer("ssss5").contains("5: Wrong\n"));
    }
}