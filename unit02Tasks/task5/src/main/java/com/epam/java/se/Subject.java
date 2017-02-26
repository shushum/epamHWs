package com.epam.java.se;

/**
 * Created by Yegor on 26.02.2017.
 */
public enum Subject {
    MATH {
        public <T extends Number> int setGrade(T grade) {
            if (isIllegalGrade(grade)) {
                illegalGradeException();
            }
            return grade.intValue();
        }
    },
    PHYSICS {
        public <T extends Number> double setGrade(T grade) {
            if (isIllegalGrade((T) grade)) {
                illegalGradeException();
            }
            return grade.doubleValue();
        }
    },

    STATISTICS {
        public <T extends Number> int setGrade(T grade) {
            if (isIllegalGrade(grade)) {
                illegalGradeException();
            }
            return grade.intValue();
        }
    },
    CHEMISTRY {
        public <T extends Number> double setGrade(T grade) {
            if (isIllegalGrade(grade)) {
                illegalGradeException();
            }
            return grade.doubleValue();
        }
    };

    private static <T extends Number> boolean isIllegalGrade(T grade) {
        return grade.intValue() < 0 || grade.intValue() > 10;
    }

    private static void illegalGradeException() {
        String error = "Grades must be in decimal system.";
        throw new IllegalArgumentException(error);
    }



}

