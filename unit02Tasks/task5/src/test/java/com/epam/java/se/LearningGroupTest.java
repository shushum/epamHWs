package com.epam.java.se;

import org.junit.Test;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 26.02.2017.
 */
public class LearningGroupTest {

    @Test
    public void initiateLearningGroupTest() {
        Student peter = new Student("Peter", "Jackson");
        Student sam = new Student("Samuel", "Jackson");

        ArrayList<Student> list = new ArrayList<>();
        list.add(peter);
        list.add(sam);

        LearningGroup mathClass = new LearningGroup(Subject.MATH, list);

        assertTrue(mathClass.getSubject().equals(Subject.MATH));

        assertTrue(mathClass.getStudents().contains(peter));
        assertTrue(mathClass.getStudents().contains(sam));

        assertTrue(mathClass.getStudentGrade(peter).toDouble() == 0);

    }

    @Test
    public void rateStudent() {
        Student peter = new Student("Peter", "Jackson");
        Student sam = new Student("Samuel", "Jackson");

        ArrayList<Student> list = new ArrayList();
        list.add(peter);
        list.add(sam);

        LearningGroup mathClass = new LearningGroup(Subject.MATH, list);

        mathClass.rateStudent(peter, new Grade<Integer>(4));

        assertTrue(mathClass.getStudentGrade(peter).equals(new Grade<Integer>(4)));
        assertFalse(mathClass.getStudentGrade(peter).equals(new Grade<Double>(4.0)));

        assertTrue(mathClass.getStudentGrade(sam).equals(new Grade<Integer>(0)));

    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalTypeRateStudent() {
        Student peter = new Student("Peter", "Jackson");
        Student sam = new Student("Samuel", "Jackson");

        ArrayList<Student> list = new ArrayList<>();
        list.add(peter);
        list.add(sam);

        LearningGroup mathClass = new LearningGroup(Subject.MATH, list);

        mathClass.rateStudent(peter, new Grade<Double>(4.0));

    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalGradeAmountRateStudent() {
        Student peter = new Student("Peter", "Jackson");
        Student sam = new Student("Samuel", "Jackson");

        ArrayList<Student> list = new ArrayList<>();
        list.add(peter);
        list.add(sam);

        LearningGroup mathClass = new LearningGroup(Subject.MATH, list);

        mathClass.rateStudent(peter, new Grade<Integer>(12));
    }
}