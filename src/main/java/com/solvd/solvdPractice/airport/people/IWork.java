package com.solvd.solvdPractice.airport.people;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface IWork {
    Logger LOGGER = LogManager.getLogger(IWork.class);

    void goToWork();
    default void finishWork() {
        LOGGER.info("Finishing work.");
    }
}
