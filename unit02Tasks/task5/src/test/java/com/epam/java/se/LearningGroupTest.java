package com.epam.java.se;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Yegor on 26.02.2017.
 */
public class LearningGroupTest {

    @Test
    public void initialGradeTest() {
        Student peter = new Student("Peter", "Jackson");

        ArrayList<Student> list = new ArrayList();
        list.add(peter);

        LearningGroup lg = new LearningGroup(Subject.MATH,list);

        System.out.println(lg.getGrades());
        System.out.println(list.contains(peter));
    }



}