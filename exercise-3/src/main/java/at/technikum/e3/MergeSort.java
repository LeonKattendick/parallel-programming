package at.technikum.e3;

import java.util.concurrent.RecursiveAction;

public class MergeSort extends RecursiveAction {

    private final int threshold;

    private int[] elements;

    private final int left, right;

    public MergeSort(int threshold, int[] elements, int left, int right) {
        this.threshold = threshold;
        this.elements = elements;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {

    }

    public int[] sort(int[] elements, int left, int right) {
        if (left == right) return new int[]{elements[left]};

        int middle = left + (right - left) / 2;

        int[] leftArray = sort(elements, left, middle);
        int[] rightArray = sort(elements, middle + 1, right);

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

    public int getThreshold() {
        return this.threshold;
    }

    public int[] getElements() {
        return this.elements;
    }

    public static void main(String[] args) {

        int MIN_LENGTH = 1_000_000;
        int MAX_LENGTH = 10_000_000;

        for (int length = MIN_LENGTH; length <= MAX_LENGTH; length += MIN_LENGTH) {
            ListUtil.executeSortAlgorithm(false, 0, length);
        }
    }
}
