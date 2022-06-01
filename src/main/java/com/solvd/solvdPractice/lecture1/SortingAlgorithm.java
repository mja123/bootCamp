package com.solvd.solvdPractice.lecture1;

public class SortingAlgorithm {

    public static int[] selectionSorting (int[] numbers) {

        int auxiliary = 0;

        System.out.println("The unsorted array: ");
        printArray(numbers);
        //Compare the number in the position of the interation and change the place if in other position other number
        //is bigger, g.i:
        // values       iterations  values
        //{4, 3, 2, 1}   1  |  2   {1, 3, 4, 2}
        //{3, 4, 2, 1}   1  |  2   {1, 2, 4, 3}
        //{2, 4, 3, 1}   1  |  3   {1, 2, 3, 4}
        //{1, 4, 3, 2}   1  |  4   {1, 2, 3, 4}
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (numbers[i] < numbers[j]) {

                    auxiliary = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = auxiliary;
                }
            }
        }
        System.out.println("The sorted array: ");
        printArray(numbers);
        return numbers;
    }

    public static int[] quickSort(int[] numbers) {
        System.out.println("The unsorted array: ");
        printArray(numbers);

        quickSort(numbers, numbers.length - 1, 0);

        System.out.println("The sorted array: ");
        printArray(numbers);
        return numbers;
    }

    private static void quickSort (int[] numbers, int highIndex, int lowIndex) {

        if (lowIndex >= highIndex) {
            //The method finish when both indexes are equals.
            return;
        }
        int pivot = numbers[highIndex];

        //Get the leftest number in the partition.
        int leftPointer = partition(numbers, highIndex, lowIndex, pivot);

        quickSort(numbers, leftPointer - 1, lowIndex);
        //Sorting the left subarray.
        quickSort(numbers, highIndex, leftPointer + 1);
        //Sorting the right subarray.
    }

    private static int partition(int[] numbers, int highIndex, int lowIndex, int pivot) {
        //The goal of this method is sort the pivot (the leftest number in the array).
        int rightPointer = highIndex;
        int leftPointer = lowIndex;

        while (leftPointer < rightPointer) {
            while (numbers[leftPointer] <= pivot && leftPointer < rightPointer) {
                leftPointer++;
            }
            while (numbers[rightPointer] >= pivot && leftPointer < rightPointer) {
                rightPointer--;
            }
            swapElements(numbers, leftPointer, rightPointer);
        }
        swapElements(numbers, leftPointer, highIndex);
        return leftPointer;
    }

    private static void swapElements(int[] numbers, int index1, int index2) {
        //change the order of the numbers.
        int auxiliary = numbers[index1];
        numbers[index1] = numbers[index2];
        numbers[index2] = auxiliary;
    }

    private static void printArray(int[] numbers) {
        //This method shows the values in the array.
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println(" ");
    }
}
