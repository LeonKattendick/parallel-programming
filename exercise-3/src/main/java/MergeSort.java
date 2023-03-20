import util.ListUtil;
import util.SortAlgorithm;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeSort implements SortAlgorithm {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private final int threshold;

    private final ListUtil.ParallelType type;

    public MergeSort(int threshold, ListUtil.ParallelType type) {
        this.threshold = threshold;
        this.type = type;
    }

    public int[] sort(int[] elements, int left, int right) {
        if (left == right) return new int[]{elements[left]};

        int amountOfElements = right - left;
        int middle = left + (right - left) / 2;

        int[] leftArray, rightArray;
        try {
            if (type == ListUtil.ParallelType.SEQUENTIAL || (type == ListUtil.ParallelType.THRESHOLD && amountOfElements <= threshold)) {
                leftArray = sort(elements, left, middle);
                rightArray = sort(elements, middle + 1, right);
            } else {
                leftArray = executorService.submit(() -> sort(elements, left, middle)).get();
                rightArray = executorService.submit(() -> sort(elements, middle + 1, right)).get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return merge(leftArray, rightArray);
    }

    private int[] merge(int[] leftArray, int[] rightArray) {
        int leftLength = leftArray.length;
        int rightLength = rightArray.length;

        int targetPosition = 0, leftPosition = 0, rightPosition = 0;
        int[] target = new int[leftLength + rightLength];

        while (leftPosition < leftLength && rightPosition < rightLength) {
            int leftValue = leftArray[leftPosition];
            int rightValue = rightArray[rightPosition];

            if (leftValue <= rightValue) {
                target[targetPosition++] = leftValue;
                leftPosition++;
            } else {
                target[targetPosition++] = rightValue;
                rightPosition++;
            }
        }

        while (leftPosition < leftLength) {
            target[targetPosition++] = leftArray[leftPosition++];
        }

        while (rightPosition < rightLength) {
            target[targetPosition++] = rightArray[rightPosition++];
        }

        return target;
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
        //   sequentialMergeSort();
        thresholdMergeSort();
        unlimitedMergeSort();
    }

    private static void sequentialMergeSort() {

        int MIN_LENGTH = 1_000_000;
        int MAX_LENGTH = 10_000_000;

        for (int length = MIN_LENGTH; length <= MAX_LENGTH; length += MIN_LENGTH) {
            MergeSort mergeSort = new MergeSort(0, ListUtil.ParallelType.SEQUENTIAL);
            ListUtil.executeSortAlgorithm(mergeSort, length);
        }
    }

    private static void unlimitedMergeSort() {

        int MIN_LENGTH = 10_000;
        int MAX_LENGTH = 100_000;

        for (int length = MIN_LENGTH; length <= MAX_LENGTH; length += MIN_LENGTH) {
            MergeSort mergeSort = new MergeSort(0, ListUtil.ParallelType.UNLIMITED);
            ListUtil.executeSortAlgorithm(mergeSort, length);
        }
    }

    private static void thresholdMergeSort() {

        List<Integer> thresholds = List.of(10000, 50000, 100000);

        for (int threshold : thresholds) {
            MergeSort mergeSort = new MergeSort(threshold, ListUtil.ParallelType.THRESHOLD);
            ListUtil.executeSortAlgorithm(mergeSort, 10_000_000);
        }
    }
}
