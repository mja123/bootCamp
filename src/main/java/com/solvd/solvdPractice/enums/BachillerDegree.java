package com.solvd.solvdPractice.enums;

import com.solvd.solvdPractice.collections.exceptions.ElementNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class BachillerDegree {

    private static final Logger LOGGER = LogManager.getLogger(BachillerDegree.class);
    private EDegrees degree;
    private Set<ESubjects> subjects = new HashSet<>();

    public BachillerDegree(EDegrees degree) {
        this.degree = degree;
        setSubjects();
    }

    public void showAllSubjects() {
        subjects.forEach(p -> LOGGER.info("The subject: " + p.name()
                + " has: "+ p.getHOURS() + " hours, and the teacher is: "
                + p.getTEACHER()));
    }

    private void setSubjects() {
        switch (degree) {
            case INFORMATICS:
                subjects.add(ESubjects.ALGEBRA);
                subjects.add(ESubjects.PROGRAMMING2);
                subjects.add(ESubjects.CALCULUS2);
                subjects.add(ESubjects.CLOUD_COMPUTING);
                break;
            case MATHEMATICS:
                subjects.add(ESubjects.ALGEBRA);
                subjects.add(ESubjects.CALCULUS2);
                subjects.add(ESubjects.GEOMETRY);
                subjects.add(ESubjects.ANALYTICAL_GEOMETRY);
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BachillerDegree that = (BachillerDegree) o;
        return degree == that.degree && Objects.equals(subjects, that.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(degree, subjects);
    }
}
