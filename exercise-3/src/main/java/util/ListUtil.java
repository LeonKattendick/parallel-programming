package util;

import java.util.Random;

public class ListUtil {

    private static final int ITERATIONS = 1;

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

    public static void executeSortAlgorithm(SortAlgorithm sortAlgorithm, int length) {
        int[] elements = new int[length];

        Random r = new Random(0);
        for (int i = 0; i < length; i++) {
            elements[i] = r.nextInt();
        }

        long summedTime = 0;
        for (int i = 0; i < ITERATIONS; i++) {

            Stopwatch stopwatch = new Stopwatch();
            stopwatch.start();
            int[] sorted = sortAlgorithm.sort(elements, 0, length - 1);
            summedTime += stopwatch.stop();

            if (!ListUtil.isSorted(sorted)) System.out.println("#### LISTE NICHT SORTIERT ####");
        }
        System.out.printf("Length %-10d | Threshold: %-10d = %.2fms\n", length, sortAlgorithm.getThreshold(), summedTime / (double) ITERATIONS);
    }
}
