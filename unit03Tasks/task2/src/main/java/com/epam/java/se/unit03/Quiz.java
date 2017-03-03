package com.epam.java.se.unit03;


import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Yegor on 03.03.2017.
 */
public class Quiz {

    ResourceBundle questions;

    public Quiz(){
       // Locale locale = new Locale("ru");
        this.questions = ResourceBundle.getBundle("questions");
    }

    public static void main(String[] args) {
        Quiz testQuiz = new Quiz();


            System.out.println(testQuiz.questions.getString("s1"));

    }

}
