import util.ListUtil;
import util.SortAlgorithm;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeSort implements SortAlgorithm {

    private final ExecutorService executorService = Executors.newFixedThreadPool(16);

    private final int threshold;

    public MergeSort(int threshold) {
        this.threshold = threshold;
    }

    public int[] sort(int[] elements, int left, int right) {
        if (left == right) return new int[]{elements[left]};

        int middle = left + (right - left) / 2;

        int[] leftArray = sort(elements, left, middle);
        int[] rightArray = sort(elements, middle + 1, right);

        return merge(leftArray, rightArray);
    }

    @Override
    public int getThreshold() {
        return this.threshold;
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

    public static void main(String[] args) {

        int MIN_LENGTH = 100_000;
        int MAX_LENGTH = 100_000_000;

        List<Integer> thresholds = List.of(0);

        for (int length = MIN_LENGTH; length <= MAX_LENGTH; length += MIN_LENGTH) {
            for (int threshold : thresholds) {
                MergeSort mergeSort = new MergeSort(threshold);
                ListUtil.executeSortAlgorithm(mergeSort, length);
            }
        }
    }
}
