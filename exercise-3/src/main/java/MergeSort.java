import com.sun.source.tree.NewArrayTree;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeSort {

    private final ExecutorService executorService = Executors.newFixedThreadPool(16);

    private final int threshold;

    public MergeSort(int threshold) {
        this.threshold = threshold;
    }

    public int[] mergeSort(int[] elements, int left, int right) {
        // End of recursion reached?
        if (left == right) return new int[]{elements[left]};

        int middle = left + (right - left) / 2;
        int[] leftArray = mergeSort(elements, left, middle);
        int[] rightArray = mergeSort(elements, middle + 1, right);
        return merge(leftArray, rightArray);
    }

    private int[] merge(int[] leftArray, int[] rightArray) {
        int leftLen = leftArray.length;
        int rightLen = rightArray.length;

        int[] target = new int[leftLen + rightLen];
        int targetPos = 0;
        int leftPos = 0;
        int rightPos = 0;

        // As long as both arrays contain elements...
        while (leftPos < leftLen && rightPos < rightLen) {
            // Which one is smaller?
            int leftValue = leftArray[leftPos];
            int rightValue = rightArray[rightPos];
            if (leftValue <= rightValue) {
                target[targetPos++] = leftValue;
                leftPos++;
            } else {
                target[targetPos++] = rightValue;
                rightPos++;
            }
        }
        // Copy the rest
        while (leftPos < leftLen) {
            target[targetPos++] = leftArray[leftPos++];
        }
        while (rightPos < rightLen) {
            target[targetPos++] = rightArray[rightPos++];
        }
        return target;
    }

    static class Stopwatch {

        private long startTime;

        public void start() {
            this.startTime = System.currentTimeMillis();
        }

        public long stop() {
            return System.currentTimeMillis() - this.startTime;
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Gebe die Länge der Liste (und den Thread Threshold) als Argument mit.");
            return;
        }

        int length = Integer.parseInt(args[0]);
        int threshold = args.length == 2 ? Integer.parseInt(args[1]) : Integer.MAX_VALUE;
        int seed = 0;
        Stopwatch stopwatch = new Stopwatch();

        int[] elements = new int[length];

        Random r = new Random(seed);
        for (int i = 0; i < length; i++) {
            elements[i] = r.nextInt(length * 1000);
        }
        System.out.println();
        System.out.println("Starting MergeSort with length = " + length + ", threshold = " + threshold);
        System.out.println();

        MergeSort sort = new MergeSort(threshold);
        stopwatch.start();
        int[] sorted = sort.mergeSort(elements, 0, length-1);
        double sortTime = stopwatch.stop();

        //System.out.println("Unsorted List: " + Arrays.toString(elements));
        // System.out.println("Sorted List: " + Arrays.toString(sorted));
        System.out.println(String.format("Needed calculationTime: %.10fms", sortTime));
    }
}
