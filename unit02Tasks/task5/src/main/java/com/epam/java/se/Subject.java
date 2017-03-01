package com.epam.java.se;

/**
 * Created by Yegor on 26.02.2017.
 */
public enum Subject {
    MATH(Integer.class),
    STATISTICS(Double.class),
    PHYSICS(Double.class),
    IT(Integer.class);

    private final Class gradeType;

    Subject(Class gradeType) {
        this.gradeType = gradeType;
    }

    public static void gradeTypeCheck (Subject subject, Grade grade) {
        if (!(grade.getGrade().getClass().equals(subject.gradeType))) {
            String message = String.format("Grade (%s) for '%s' must be %s.",
                    grade.getGrade().toString(), subject.toString(), subject.gradeType.getSimpleName());
            throw new IllegalArgumentException(message);
        }
    }

}

