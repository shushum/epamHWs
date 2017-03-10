package com.epam.java.se.unit04;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Created by Yegor on 10.03.2017.
 */
public class Actor {
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private int age;
    private Gender gender;
    private int height;
    private int amountOfLeadRoles;

    public Actor(String firstName, String lastName, LocalDate dateOfBirth, int age, Gender gender, int height) {

        personIsNotNull(firstName, lastName, dateOfBirth);
        ageAndHeightAreValid(age, height);

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.gender = gender;
        this.height = height;
        amountOfLeadRoles = 0;
    }

    public void addLeadRole(){
        amountOfLeadRoles++;
    }

    public int getAmountOfLeadRoles() {
        return amountOfLeadRoles;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    private void personIsNotNull(String firstName, String lastName, LocalDate dateOfBirth) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(dateOfBirth);
    }

    private void ageAndHeightAreValid(int age, int height) {
        if (age < 0) {
            throw new IllegalArgumentException(String.format("Age [%d] must be positive!", age));
        }
        if (height > 272){
            throw new IllegalArgumentException(String.format("You might want to contact 'Guinness World Records' firstly" +
                    "with such height [%d]. That's incredible!", height));
        }
    }
}

enum Gender {
    MALE,
    FEMALE
}

