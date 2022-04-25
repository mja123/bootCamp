package com.solvd.solvdPractice.personal_practice.multithreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Call implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Call.class);
    private Person person1 = new Person("Matias");
    private Person person2 = new Person ("Milagros");

    public void run() {
        LOGGER.info("The call is starting...");

        Thread.currentThread().getName();
    }
}
