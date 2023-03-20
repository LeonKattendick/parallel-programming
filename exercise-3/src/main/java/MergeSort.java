import util.ListUtil;
import util.SortAlgorithm;

public class MergeSort implements SortAlgorithm {

    private final int threshold;

    public MergeSort(int threshold) {
        this.threshold = threshold;
    }

    public int[] sort(int[] elements, int left, int right) {
        if (left == right) return new int[]{elements[left]};

        int middle = left + (right - left) / 2;

        int[] leftArray, rightArray;

        leftArray = sort(elements, left, middle);
        rightArray = sort(elements, middle + 1, right);

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

    public static void main(String[] args) {

        int MIN_LENGTH = 1_000_000;
        int MAX_LENGTH = 10_000_000;

        for (int length = MIN_LENGTH; length <= MAX_LENGTH; length += MIN_LENGTH) {
            MergeSort mergeSort = new MergeSort(0);
            ListUtil.executeSortAlgorithm(mergeSort, length);
        }
    }
}
