import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class QuickSort extends RecursiveAction {

    private final int threshold;

    private final int[] elements;

    private final int left, right;

    public QuickSort(int threshold, int[] elements, int left, int right) {
        this.threshold = threshold;
        this.elements = elements;
        this.left = left;
        this.right = right;
    }

    @Override
    protected void compute() {
        quickSort(elements, left, right);
    }

    private void quickSort(int[] elements, int left, int right) {

        int elementsLeft = right - left;
        int index = partition(elements, left, right);

        if (elementsLeft <= getThreshold()) {
            if (left < index - 1) {
                quickSort(elements, left, index - 1);
            }
            if (index < right) {
                quickSort(elements, index, right);
            }
        } else {
            ForkJoinTask<Void> fork = null;
            if (left < index - 1) {
                fork = new QuickSort(getThreshold(), elements, left, index - 1).fork();
            }
            if (index < right) {
                new QuickSort(getThreshold(), elements, index, right).invoke();
            }

            if (fork != null) fork.join();
        }
    }

    private int partition(int[] elements, int left, int right) {
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
            ListUtil.executeSortAlgorithm(true, 50000, length);
        }
    }
}
