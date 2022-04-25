package com.solvd.solvdPractice.enums;

import com.solvd.solvdPractice.enums.customEnums.ESubjects;
import com.solvd.solvdPractice.enums.customLambdas.IAverage;
import com.solvd.solvdPractice.enums.customLambdas.IStudy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Student {

    private static final Logger LOGGER = LogManager.getLogger(Student.class);
    private BachillerDegree degree;
    private Double mark;
    private Integer id;

    //region constructors
    public Student(BachillerDegree degree, Integer id, Double mark) {
        this.degree = degree;
        this.id = id;
        this.mark = mark;
    }

    public Student(BachillerDegree degree, Double mark, Integer id) {
        this.degree = degree;
        this.mark = mark;
        this.id = id;
    }
    //endregion

    //region lambdas
    public void goToLibrary(String book) {
        Consumer<String> read = r -> LOGGER.info("Reading: " + book);

        read.accept(book);
    }

    public String goToClass() {
        Supplier<String> takeClass = () -> "Taking class";

        return takeClass.get();
    }


    public Boolean passTheCourse() {

        Predicate<Double> status = i ->  i >= 6;

        return status.test(this.mark);
    }

    public void studyingSubject(ESubjects subject) {
        IStudy studying = s -> LOGGER.info("Studying " + s.name());

        studying.study(subject);
    }

    public Double averageCalculus(ArrayList<Double> marks) {

        IAverage<Double> finalAverage = m ->
            m.stream().reduce(0.0, Double::sum)/m.size();

        return finalAverage.average(marks);
    }
    //endregion

    //region getters and setters
    public BachillerDegree getDegree() {
        return degree;
    }

    public void setDegree(BachillerDegree degree) {
        this.degree = degree;
    }
    //endregion
}
