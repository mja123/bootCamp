package com.solvd.solvdPractice.airport.fly;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface IFly {
    Logger LOGGER = LogManager.getLogger(IFly.class);

    void arrive();
    
    default void startFlying() {
        LOGGER.info("I'm flying.");
    }
}
