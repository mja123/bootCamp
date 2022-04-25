package com.solvd.solvdPractice.multiThreading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadExample {

    private static final Logger LOGGER = LogManager.getLogger(ThreadExample.class);

    public static void main(String[] args) {

        Car c1 = new Car();
        Car c2 = new Car();
        Car c3 = new Car();
        Car c4 = new Car();
        Car c5 = new Car();
        Car1 c6 = new Car1();
        Car1 c7 = new Car1();

        ThreadPoolExecutor tp = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        tp.submit(() -> {
            LOGGER.info("Executing threads");
        });
        tp.execute(c1);
        tp.execute(c2);
        tp.execute(c3);
        tp.execute(c4);
        tp.execute(c5);
        tp.execute(c6);
        tp.execute(c7);
        tp.shutdown();

    }

    public static class Car extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("Called from the Car Class");
        }
    }

    public static class Car1 implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("Called from the Car1 Class");
        }
    }
}

