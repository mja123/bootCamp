package com.solvd.university.daos.mySqlImplementation.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface IListener {
    Logger LOGGER = LogManager.getLogger(IListener.class);
    default void notification() {
        LOGGER.info("There is an available connection.");
    }
}
