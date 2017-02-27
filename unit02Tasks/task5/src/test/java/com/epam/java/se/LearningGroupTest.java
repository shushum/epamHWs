package com.epam.java.se;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 26.02.2017.
 */
public class LearningGroupTest {

    @Test
    public void initialLearningGroupTest() {
        Student peter = new Student("Peter", "Jackson");

        ArrayList<Student> list = new ArrayList();
        list.add(peter);

        LearningGroup lg = new LearningGroup(Subject.MATH, list);

        System.out.println(lg.getGrades());
        System.out.println(list.contains(peter));
    }

    @Test
    public void rateStudent() {
        Student peter = new Student("Peter", "Jackson");
        Student sam = new Student("Samuel", "Jackson");

        ArrayList<Student> list = new ArrayList();
        list.add(peter);
        list.add(sam);

        LearningGroup mathClass = new LearningGroup(Subject.MATH, list);

        System.out.println(mathClass.getGrades());

        mathClass.rateStudent(peter, new Grade(4));

        System.out.println(mathClass.getGrades());
    }

    @Test
    public void illegalTypeRateStudent() {
        Student peter = new Student("Peter", "Jackson");
        Student sam = new Student("Samuel", "Jackson");

        ArrayList<Student> list = new ArrayList();
        list.add(peter);
        list.add(sam);

        LearningGroup mathClass = new LearningGroup(Subject.MATH, list);

        try {
            mathClass.rateStudent(peter, new Grade(4.0));
        } catch (IllegalArgumentException e){
            System.err.println("Wrong type of grade.");
        }
    }

    @Test
    public void illegalGradeRateStudent() {
        Student peter = new Student("Peter", "Jackson");
        Student sam = new Student("Samuel", "Jackson");

        ArrayList<Student> list = new ArrayList();
        list.add(peter);
        list.add(sam);

        LearningGroup mathClass = new LearningGroup(Subject.MATH, list);

        try {
            mathClass.rateStudent(peter, new Grade(12));
        } catch (IllegalArgumentException e){
            System.err.println("Grades must be from 0 to 10.");
        }
    }
}