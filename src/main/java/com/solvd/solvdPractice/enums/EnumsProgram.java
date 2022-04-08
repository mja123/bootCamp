package com.solvd.solvdPractice.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnumsProgram {
    private static final Logger LOGGER = LogManager.getLogger(EnumsProgram.class);

    public static void main(String[] args) {
        BachillerDegree informatics = new BachillerDegree(EDegrees.INFORMATICS);

        Student student1 = new Student(informatics, 1, 7.6);

        student1.getDegree().showAllSubjects();
        student1.goToLibrary("Calculus' book");
        LOGGER.info(student1.goToClass());

        LOGGER.info("The student pass the course? " + student1.passTheCourse());

    }
}
