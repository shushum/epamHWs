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

    public Actor(String firstName, String lastName, LocalDate dateOfBirth, int age, Gender gender, int height) {

        personIsNotNull(firstName, lastName, dateOfBirth);
        ageAndHeightAreValid(age, height);

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.gender = gender;
        this.height = height;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Actor actor = (Actor) o;

        if (age != actor.age) return false;
        if (height != actor.height) return false;
        if (!firstName.equals(actor.firstName)) return false;
        if (!lastName.equals(actor.lastName)) return false;
        if (!dateOfBirth.equals(actor.dateOfBirth)) return false;
        return gender == actor.gender;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + age;
        result = 31 * result + gender.hashCode();
        result = 31 * result + height;
        return result;
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

