
package com.solvd.solvdPractice.airport.fly;

import com.solvd.solvdPractice.airport.people.IWork;
import com.solvd.solvdPractice.airport.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Pilot extends Person implements IWork {
    private static final Logger LOGGER = LogManager.getLogger(Pilot.class);
    private Integer yearsOfExperience;

    //region constructors

    public Pilot() {
    }

    public Pilot(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Pilot(String name, Integer age, String nationality, String occupation, Integer yearsOfExperience) {
        super(name, age, nationality, occupation);
        this.yearsOfExperience = yearsOfExperience;
    }
    //endregion

    @Override
    public void goToWork() {
        LOGGER.info("Going to work");
    }

    @Override
    public String toString() {
        super.toString();
        return "Pilot{" +
                "yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
