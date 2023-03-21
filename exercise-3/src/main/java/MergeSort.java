import java.util.concurrent.ForkJoinTask;
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
        elements = mergeSort(elements, left, right);
    }

    private int[] mergeSort(int[] elements, int left, int right) {
        if (left == right) return new int[]{elements[left]};

        int elementsLeft = right - left;
        int middle = left + elementsLeft / 2;
        int[] leftArray, rightArray;


        if (elementsLeft <= getThreshold()) {
            leftArray = mergeSort(elements, left, middle);
            rightArray = mergeSort(elements, middle + 1, right);
        } else {
            MergeSort leftMerge = new MergeSort(getThreshold(), elements, left, middle);
            MergeSort rightMerge = new MergeSort(getThreshold(), elements, middle + 1, right);
            ForkJoinTask<Void> fork = leftMerge.fork();
            rightMerge.invoke();

            fork.join();
            leftArray = leftMerge.getElements();
            rightArray = rightMerge.getElements();
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
            ListUtil.executeSortAlgorithm(false, 50000, length);
        }
    }
}
