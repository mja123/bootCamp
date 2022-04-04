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
        System.out.println("Tuning on the cash register.");
        //LOGGER.trace("Tuning on the cash register.");
    }
    public void startWorking() {
        System.out.println("Charging to clients");
        //LOGGER.trace("Charging to clients");
    }
    public void finishWork() {
        System.out.println("Turning off the cash register, counting the money.");
        //LOGGER.trace("Tuning the cash register, counting the money.");
    }

    public Integer getCashierId() {
        return cashierId;
    }
}
