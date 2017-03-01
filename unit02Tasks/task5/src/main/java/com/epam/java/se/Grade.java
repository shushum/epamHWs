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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade<?> grade1 = (Grade<?>) o;

        return grade != null ? grade.equals(grade1.grade) : grade1.grade == null;
    }

    @Override
    public int hashCode() {
        return grade != null ? grade.hashCode() : 0;
    }
}
