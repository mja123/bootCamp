package com.solvd.solvdPractice.airport.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SecurityGuard extends Person implements IWork {
    private static final Logger LOGGER = LogManager.getLogger(SecurityGuard.class);
    private Boolean isInAPlane;

    //region constructors
    public SecurityGuard() {
    }

    public SecurityGuard(Boolean isInAPlane) {
        this.isInAPlane = isInAPlane;
    }

    public SecurityGuard(String name, Integer age, String nationality, String occupation, Boolean isInAPlane) {
        super(name, age, nationality, occupation);
        this.isInAPlane = isInAPlane;
    }
    //endregion

    public Boolean getInAPlane() {
        return isInAPlane;
    }

    @Override
    public void goToWork() {
        LOGGER.info("Going to work");
    }


    public void setInAPlane(Boolean inAPlane) {
        isInAPlane = inAPlane;
    }
}
