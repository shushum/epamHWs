package com.epam.java.se;

import java.util.ArrayList;

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
        this.grades = new ArrayList<>(students.size());
    }

    public void rateStudent(Student student, T grade) {
        //nottull todo

        if (students.contains(student)) {
            gradeLegalityCheck(grade);
            grades.set(students.indexOf(student), grade);
        }
        return;

    }

    private void gradeLegalityCheck(T grade) {
        //nottull todo

        switch (subject) {
            case IT:
            case MATH: {
                if (!(grade instanceof Integer)) {
                    throw new IllegalArgumentException();
                }
                // new exception on value of grade
                break;
            }
            case PHYSICS:
            case STATISTICS: {
                if (!(grade instanceof Double)) {
                    throw new IllegalArgumentException();
                }
                break;
            }
        }

    }

   /* private<T extends Number> double setDoubleGrade(Grade grade) {
        if (isIllegalGrade(grade)) {
            illegalGradeException();
        }
        return grade.doubleValue();
    }

    private <T extends Number> int setIntGrade(T grade) {
        if (isIllegalGrade(grade)) {
            illegalGradeException();
        }
        return grade.intValue();
    }

    private static <T extends Number> boolean isIllegalGrade(T grade) {
        return grade.intValue() < 0 || grade.intValue() > 10;
    }

    private static void illegalGradeException() {
        String error = "Grades must be in decimal system.";
        throw new IllegalArgumentException(error);
    }*/

    public ArrayList<T> getGrades() {
        return grades;
    }
}
