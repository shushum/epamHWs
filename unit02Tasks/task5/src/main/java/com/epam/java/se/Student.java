package com.epam.java.se;

import java.util.ArrayList;

/**
 * Created by Yegor on 26.02.2017.
 */
public class Student {
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String compareStudentGradesByEyes(ArrayList<LearningGroup> existingGroups){
        ArrayList<LearningGroup> studentGroups = searchForStudentGroups(existingGroups);

        StringBuilder result = new StringBuilder();
        result.append(firstName + " " + lastName + "'s grades are:\n");

        for (LearningGroup group: studentGroups) {

            String subject = group.getSubject().toString();
            String grade = group.getStudentGrades(this).getGrade().toString();

            result.append("Subject: " + subject + ", Grade: " + grade +"\n");
        }
        return result.toString();
    }

    private ArrayList<LearningGroup> searchForStudentGroups (ArrayList<LearningGroup> existingGroups){
        ArrayList<LearningGroup> groups = new ArrayList<>();

        for (LearningGroup group: existingGroups) {
            if (group.getStudents().contains(this)){
                groups.add(group);
            }
        }

        return groups;
    }

}
