package com.solvd.solvdPractice.airport.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Crew extends Person implements IWork {
    private static final Logger LOGGER = LogManager.getLogger(SecurityGuard.class);
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
        LOGGER.info("Going to work");
    }

    @Override
    public String toString() {
        super.toString();
        return "Crew{" +
                "job='" + job + '\'' +
                '}';
    }
}
