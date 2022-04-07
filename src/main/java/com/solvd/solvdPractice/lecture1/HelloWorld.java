package com.solvd.solvdPractice.lecture1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HelloWorld {
    private final static Logger LOGGER = LogManager.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        LOGGER.info("Hello world");

        try {
            if (args.length <= 0) {
                throw new IllegalArgumentException("Arguments out of range");
            }
            int[] numbers = Arrays.stream(args)
                    .flatMapToInt(num -> IntStream.of(Integer.parseInt(num)))
                    .toArray();

            //SortingAlgorithms.selectionSorting(numbers);
            SortingAlgorithm.quickSort(numbers);

        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
