package com.epam.java.se;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by Yegor on 26.02.2017.
 */
public class LearningGroup<T extends Number> {
    private Subject subject;
    private ArrayList<Student> students;
    private ArrayList<T> grades;

    public LearningGroup(Subject subject, ArrayList<Student> students) {
        this.subject = subject;
        this.students = students;
        this.grades = new ArrayList<>(Collections.nCopies(students.size(), null));
    }

    public void rateStudent(Student student, T grade) {
        Objects.requireNonNull(grade);
        Objects.requireNonNull(student);

        if (students.contains(student)) {
            gradeLegalityCheck(grade);
            grades.set(students.indexOf(student), grade);
        }
        return;

    }

    public T getStudentGrades(Student student) {

        if (students.contains(student)) {

            T grade = grades.get(students.indexOf(student));

            if (grade == null) {
                nullGradeException(student);
            }
            return grade;
        }

        String message = String.format("Student %s is not in this Learning Group.",
                student.getLastName());
        throw new IllegalArgumentException(message);
    }

    public ArrayList<T> getGrades() {
        return grades;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Subject getSubject() {
        return subject;
    }

    private void gradeLegalityCheck(T grade) {

        if (isIllegal(grade)) {
            illegalGradeException();
        }

        switch (subject) {
            case IT:
            case MATH: {
                if (!(grade instanceof Integer)) {
                    String message = String.format("Grade (%s) for '%s' must be Integer.",
                            grade.toString(), subject.toString());
                    throw new IllegalArgumentException(message);
                }
                break;
            }
            case PHYSICS:
            case STATISTICS: {
                if (!(grade instanceof Double)) {
                    String message = String.format("Grade (%s) for '%s' must be Double.",
                            grade.toString(), subject.toString());
                    throw new IllegalArgumentException(message);
                }
                break;
            }
        }
    }

    private static <T extends Number> boolean isIllegal(T grade) {
        return grade.intValue() < 0 || grade.intValue() > 10;
    }

    private static void illegalGradeException() {
        String error = "Grades must be from 0 to 10 system.";
        throw new IllegalArgumentException(error);
    }

    private static void nullGradeException(Student student) {
        String message = String.format("Student %s has no grade on this Subject.",
                student.getLastName());
        throw new IllegalArgumentException(message);
    }
}
