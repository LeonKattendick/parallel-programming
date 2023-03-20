import util.ListUtil;
import util.SortAlgorithm;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuickSort implements SortAlgorithm {

    private final ExecutorService executorService = Executors.newFixedThreadPool(16);

    private final int threshold;

    private final ListUtil.ParallelType type;

    public QuickSort(int threshold, ListUtil.ParallelType type) {
        this.threshold = threshold;
        this.type = type;
    }

    public int[] sort(int[] elements, final int left, final int right) {
        int[] array = new int[elements.length];

        for (int i = 0; i < array.length; i++) {
            array[i] = elements[i];
        }
        quickSort(array, left, right);

        return array;
    }


    private void quickSort(int[] elements, final int left, final int right) {

        int amountOfElements = right - left;
        int index = partition(elements, left, right);

        if (type == ListUtil.ParallelType.SEQUENTIAL || (type == ListUtil.ParallelType.THRESHOLD && amountOfElements <= threshold)) {
            if (left < index - 1) {
                quickSort(elements, left, index - 1);
            }
            if (index < right) {
                quickSort(elements, index, right);
            }
        } else {
            try {
                if (left < index - 1) {
                    executorService.submit(() -> quickSort(elements, left, index - 1)).get();
                }
                if (index < right) {
                    executorService.submit(() -> quickSort(elements, index, right)).get();
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
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

    @Override
    public ListUtil.ParallelType getType() {
        return type;
    }

    public static void main(String[] args) {
        thresholdQuickSort();
        sequentialQuickSort();
        unlimitedQuickSort();
    }

    private static void sequentialQuickSort() {

        int MIN_LENGTH = 1_000_000;
        int MAX_LENGTH = 10_000_000;

        for (int length = MIN_LENGTH; length <= MAX_LENGTH; length += MIN_LENGTH) {
            QuickSort quickSort = new QuickSort(0, ListUtil.ParallelType.SEQUENTIAL);
            ListUtil.executeSortAlgorithm(quickSort, length);
        }
    }

    private static void unlimitedQuickSort() {

        int MIN_LENGTH = 10_000;
        int MAX_LENGTH = 100_000;

        for (int length = MIN_LENGTH; length <= MAX_LENGTH; length += MIN_LENGTH) {
            QuickSort quickSort = new QuickSort(0, ListUtil.ParallelType.UNLIMITED);
            ListUtil.executeSortAlgorithm(quickSort, length);
        }
    }

    private static void thresholdQuickSort() {

        List<Integer> thresholds = List.of(10000, 50000, 100000);

        for (int threshold : thresholds) {
            QuickSort quickSort = new QuickSort(threshold, ListUtil.ParallelType.THRESHOLD);
            ListUtil.executeSortAlgorithm(quickSort, 10_000_000);
        }
    }
}
