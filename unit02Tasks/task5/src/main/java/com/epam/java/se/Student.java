package com.epam.java.se;

/**
 * Created by Yegor on 26.02.2017.
 */
public class Student<T extends Number> {
    private String firstName;
    private String lastName;
    private T grade;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setGrade(T grade){
        this.grade = grade;
    }
}
