package com.solvd.solvdPractice.collections.people;

public abstract class Crew extends Person implements IWork {
    private Integer yearsOfExperience;
    private Double salary;
    private String area;

    public Crew(String name, Integer age, Integer yearsOfExperience, Double salary, String area) {
        super(name, age);
        this.yearsOfExperience = yearsOfExperience;
        this.salary = salary;
        this.area = area;
    }
}
