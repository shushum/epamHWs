package com.epam.java.se;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 27.02.2017.
 */
public class StudentTest {
    @Test
    public void compareStudentGradesByEyesWorking() throws Exception {
        Student peter = new Student("Peter", "Jackson");
        Student sam = new Student("Samuel", "Jackson");

        ArrayList<Student> list1 = new ArrayList<>();
        list1.add(peter);
        list1.add(sam);

        ArrayList<Student> list2 = new ArrayList<>();
        list2.add(sam);

        LearningGroup mathClass = new LearningGroup(Subject.MATH, list1);
        LearningGroup statClass = new LearningGroup(Subject.STATISTICS, list1);
        LearningGroup itClass = new LearningGroup(Subject.IT, list2);

        mathClass.rateStudent(peter, new Grade(5));
        statClass.rateStudent(peter,new Grade(9.99));


        ArrayList<LearningGroup> groups = new ArrayList<>();
        groups.add(mathClass);
        groups.add(statClass);
        groups.add(itClass);

        System.out.println(peter.compareStudentGradesByEyes(groups));
    }

    @Test
    public void compareStudentGradesWithNoGrades() throws Exception {
        Student peter = new Student("Peter", "Jackson");
        Student sam = new Student("Samuel", "Jackson");

        ArrayList<Student> list1 = new ArrayList<>();
        list1.add(peter);
        list1.add(sam);

        ArrayList<Student> list2 = new ArrayList<>();
        list2.add(sam);

        LearningGroup mathClass = new LearningGroup(Subject.MATH, list1);
        LearningGroup statClass = new LearningGroup(Subject.STATISTICS, list1);
        LearningGroup itClass = new LearningGroup(Subject.IT, list2);

        ArrayList<LearningGroup> groups = new ArrayList<>();
        groups.add(mathClass);
        groups.add(statClass);
        groups.add(itClass);

        System.out.println(peter.compareStudentGradesByEyes(groups));
    }

}