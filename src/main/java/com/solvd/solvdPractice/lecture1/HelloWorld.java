package com.solvd.solvdPractice.lecture1;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello world");

        int[] numbers = new int[args.length];

        if (args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                numbers[i] = Integer.parseInt(args[i]);
            }
        } else {
            System.out.println("Arguments out of range");
        }

        //SortingAlgorithms.selectionSorting(numbers);
        SortingAlgorithm.quickSort(numbers);

    }
}
