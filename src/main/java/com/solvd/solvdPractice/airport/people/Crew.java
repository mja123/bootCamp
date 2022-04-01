package com.solvd.solvdPractice.airport.people;

public class Crew extends Person implements IWork {
    private String job;

    //region constructors
    public Crew() {
    }

    public Crew(String job) {
        this.job = job;
    }

    public Crew(String name, Integer age, String nationality, String occupation, String job) {
        super(name, age, nationality, occupation);
        this.job = job;
    }
    //endregion


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public void goToWork() {
        System.out.println("Going to work");
    }

    @Override
    public String toString() {
        super.toString();
        return "Crew{" +
                "job='" + job + '\'' +
                '}';
    }
}
