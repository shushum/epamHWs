package com.epam.java.se;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by Yegor on 26.02.2017.
 */
public class LearningGroup {
    private Subject subject;
    private ArrayList<Student> students;
    private ArrayList<Grade> grades;

    public LearningGroup(Subject subject, ArrayList<Student> students) {
        this.subject = subject;
        this.students = students;

        grades = new ArrayList<>(students.size());
        for (int i = 0; i < students.size(); i++) {
            grades.add(new Grade(0));
        }
    }

    public void rateStudent(Student student, Grade grade) {
        Objects.requireNonNull(grade);
        Objects.requireNonNull(student);

        if (students.contains(student)) {
            gradeLegalityCheck(grade);
            grades.set(students.indexOf(student), grade);
        }

    }

    public Grade getStudentGrades(Student student) {
        Objects.requireNonNull(student);

        if (students.contains(student)) {

            Grade grade = grades.get(students.indexOf(student));

            if (grade == null) {
                nullGradeException(student);
            }
            return grade;
        }

        String message = String.format("Student %s is not in this Learning Group.",
                student.getLastName());
        throw new IllegalArgumentException(message);
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Subject getSubject() {
        return subject;
    }

    private void gradeLegalityCheck(Grade grade) {

        if (isIllegal(grade)) {
            illegalGradeException();
        }

        switch (subject) {
            case IT:
            case MATH: {
                if (!(grade.getGrade() instanceof Integer)) {
                    String message = String.format("Grade (%s) for '%s' must be Integer.",
                            grade.toString(), subject.toString());
                    throw new IllegalArgumentException(message);
                }
                break;
            }
            case PHYSICS:
            case STATISTICS: {
                if (!(grade.getGrade() instanceof Double)) {
                    String message = String.format("Grade (%s) for '%s' must be Double.",
                            grade.toString(), subject.toString());
                    throw new IllegalArgumentException(message);
                }
                break;
            }
        }
    }

    private static boolean isIllegal(Grade grade) {
        return grade.toDouble() < 0 || grade.toDouble() > 10;
    }

    private static void illegalGradeException() {
        String error = "Grades must be from 0 to 10.";
        throw new IllegalArgumentException(error);
    }

    private static void nullGradeException(Student student) {
        String message = String.format("Student %s has no grade on this Subject.",
                student.getLastName());
        throw new IllegalArgumentException(message);
    }
}
