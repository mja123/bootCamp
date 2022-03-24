package lecture1;

import org.junit.Test;
import static org.junit.Assert.*;


public class SortingAlgorithmTest {
    private int[] result;

    @Test
    public void selectionAlgorithmIntegerNumbersOK() {
        int[] numbers = {-2, 3, 4, 0, -5};
        int[] target = {-5, -2, 0, 3, 4};

        result = SortingAlgorithm.selectionSorting(numbers);

        assertArrayEquals(target, result);
    }

    @Test
    public void quickSortIntegerNumbersOk() {
        int[] numbers = {-3, 5, 0, -4, 10};
        int[] target = {-4, -3, 0, 5, 10};

        result = SortingAlgorithm.quickSort(numbers);

        assertArrayEquals(result, target);
    }
}