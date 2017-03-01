package com.epam.java.se;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Yegor on 26.02.2017.
 */
public class LearningGroup {
    private Subject subject;
    private ArrayList<Student> students;
    private ArrayList<Grade> grades;

    //get "unchecked" warning
    //playing with annotations
    @SuppressWarnings("unchecked")
    public LearningGroup(Subject subject, ArrayList<Student> students) {
        this.subject = subject;
        this.students = students;

        grades = new ArrayList<>(students.size());
        for (int i = 0; i < students.size(); i++) {
            grades.add(new Grade<>(0));
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

    public Grade getStudentGrade(Student student) {
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

        gradeAmountCheck(grade);

        Subject.gradeTypeCheck(this.subject, grade);

    }

    private static void gradeAmountCheck(Grade grade) {

        if (grade.toDouble() < 0 || grade.toDouble() > 10) {
            String error = "Grades must be from 0 to 10.";
            throw new IllegalArgumentException(error);
        }
    }

    private static void nullGradeException(Student student) {
        String message = String.format("Student %s has no grade on this Subject.",
                student.getLastName());
        throw new IllegalArgumentException(message);
    }
}
