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

    //region getters and setters
    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    //endregion
}
