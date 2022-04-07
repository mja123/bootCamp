package com.solvd.solvdPractice.collections.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cashier extends Crew {
    private Integer cashierId;
    private static final Logger LOGGER = LogManager.getLogger(Cashier.class);

    public Cashier(String name, Integer age, Integer yearsOfExperience, Double salary, String area, Integer cashierId) {
        super(name, age, yearsOfExperience, salary, area);
        this.cashierId = cashierId;
    }

    public void prepareToStartWorking() {
        LOGGER.info("Tuning on the cash register.");

    }
    public void startWorking() {
        LOGGER.info("Charging to clients");
    }
    public void finishWork() {
        LOGGER.info("Turning off the cash register, counting the money.");
    }

    public Integer getCashierId() {
        return cashierId;
    }
}
