import util.ListUtil;
import util.SortAlgorithm;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuickSort implements SortAlgorithm {

    private final int threshold;

    public QuickSort(int threshold) {
        this.threshold = threshold;
    }

    public int[] sort(int[] elements, final int left, final int right) {
        int[] array = new int[elements.length];

        System.arraycopy(elements, 0, array, 0, array.length);
        quickSort(array, left, right);

        return array;
    }

    private void quickSort(int[] elements, final int left, final int right) {

        int index = partition(elements, left, right);

        if (left < index - 1) {
            quickSort(elements, left, index - 1);
        }
        if (index < right) {
            quickSort(elements, index, right);
        }
    }

    private int partition(int[] elements, final int left, final int right) {
        int pivot = elements[(left + right) / 2];
        int leftPosition = left;
        int rightPosition = right;

        while (leftPosition <= rightPosition) {

            while (elements[leftPosition] < pivot) {
                leftPosition++;
            }

            while (elements[rightPosition] > pivot) {
                rightPosition--;
            }

            if (leftPosition <= rightPosition) {
                int temp = elements[leftPosition];
                elements[leftPosition] = elements[rightPosition];
                elements[rightPosition] = temp;
                leftPosition++;
                rightPosition--;
            }
        }
        return leftPosition;
    }

    @Override
    public int getThreshold() {
        return this.threshold;
    }

    public static void main(String[] args) {

        int MIN_LENGTH = 1_000_000;
        int MAX_LENGTH = 10_000_000;

        for (int length = MIN_LENGTH; length <= MAX_LENGTH; length += MIN_LENGTH) {
            QuickSort quickSort = new QuickSort(0);
            ListUtil.executeSortAlgorithm(quickSort, length);
        }
    }
}
