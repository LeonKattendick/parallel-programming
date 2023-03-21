package at.technikum.e3;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class ListUtil {

    private static final int ITERATIONS = 10;

    static class Stopwatch {

        private long startTime;

        public void start() {
            this.startTime = System.currentTimeMillis();
        }

        public long stop() {
            return System.currentTimeMillis() - this.startTime;
        }
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) return false;
        }
        return true;
    }

    public static void executeSortAlgorithm(boolean isQuick, int threshold, int length) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] elements = new int[length];

        Random r = new Random(0);
        for (int i = 0; i < length; i++) {
            elements[i] = r.nextInt();
        }

        if (isQuick) runQuickSort(threshold, length, forkJoinPool, elements);
        else runMergeSort(threshold, length, forkJoinPool, elements);
    }

    private static void runQuickSort(int threshold, int length, ForkJoinPool forkJoinPool, int[] elements) {
        long summedTime = 0;
        for (int i = 0; i < ITERATIONS; i++) {

            int[] copiedElements = new int[length];
            System.arraycopy(elements, 0, copiedElements, 0, copiedElements.length);
            QuickSort quickSort = new QuickSort(threshold, copiedElements, 0, length - 1);

            Stopwatch stopwatch = new Stopwatch();
            stopwatch.start();
            forkJoinPool.invoke(quickSort);
            summedTime += stopwatch.stop();

            if (!ListUtil.isSorted(quickSort.getElements())) System.out.println("#### LISTE NICHT SORTIERT ####");

        }
        System.out.printf("%d;%d\n", length, summedTime / ITERATIONS);
    }

    private static void runMergeSort(int threshold, int length, ForkJoinPool forkJoinPool, int[] elements) {
        long summedTime = 0;
        for (int i = 0; i < ITERATIONS; i++) {

            int[] copiedElements = new int[length];
            System.arraycopy(elements, 0, copiedElements, 0, copiedElements.length);
            MergeSort mergeSort = new MergeSort(threshold, copiedElements, 0, length - 1);

            Stopwatch stopwatch = new Stopwatch();
            stopwatch.start();
            forkJoinPool.invoke(mergeSort);
            summedTime += stopwatch.stop();

            if (!ListUtil.isSorted(mergeSort.getElements())) System.out.println("#### LISTE NICHT SORTIERT ####");

        }
        System.out.printf("%d;%d\n", length, summedTime / ITERATIONS);
    }
}
