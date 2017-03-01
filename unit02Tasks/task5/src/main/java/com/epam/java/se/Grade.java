package com.epam.java.se;

/**
 * Created by Yegor on 27.02.2017.
 */
public class Grade<T extends Number> {
    private T grade;

    public Grade(T grade) {
        this.grade = grade;
    }

    public T getGrade() {
        return grade;
    }

    public double toDouble() {
        return grade.doubleValue();
    }


}
