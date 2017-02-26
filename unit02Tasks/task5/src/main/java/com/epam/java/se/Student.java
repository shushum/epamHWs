package com.epam.java.se;

import java.util.ArrayList;

/**
 * Created by Yegor on 26.02.2017.
 */
public class Student {
    private String firstName;
    private String lastName;
    private ArrayList<LearningGroup> groups = new ArrayList<>();

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

    public ArrayList<LearningGroup> searchForStudentGroups (ArrayList<LearningGroup> existingGroups){
        ArrayList<LearningGroup> groups = new ArrayList<>();

        for (LearningGroup group: existingGroups) {
            if (group.getStudents().contains(this)){
                groups.add(group);
            }
        }

        return this.groups = groups;
    }

    public ArrayList<LearningGroup> getGroups() {
        return groups;
    }
}
