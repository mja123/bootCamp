package com.solvd.solvdPractice.enums.customEnums;

import static com.solvd.solvdPractice.enums.customEnums.ETeachers.*;

public enum ESubjects {
    ALGEBRA(120.00, WILKINSON),
    PROGRAMMING2(100.00, ERASO),
    CALCULUS2(110.00, SANDEZ),
    CLOUD_COMPUTING(125.50, ONTIVERO),
    GEOMETRY(110.00, CUERVO),
    ANALYTICAL_GEOMETRY(115.50, POTICHKIN);

    private final Double HOURS;
    private final ETeachers TEACHER;

    ESubjects(Double HOURS, ETeachers TEACHER) {
        this.HOURS = HOURS;
        this.TEACHER = TEACHER;
    }

    public Double getHOURS() {
        return HOURS;
    }

    public ETeachers getTEACHER() {
        return TEACHER;
    }
}

