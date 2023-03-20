import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuickSort {

    private final ExecutorService executorService = Executors.newFixedThreadPool(16);

    private final int threshold;

    public QuickSort(int threshold) {
        this.threshold = threshold;
    }

    public int[] sort(int[] elements, final int left, final int right){
        int[] arr = new int[elements.length];
        for (int i = 0; i < arr.length; i++){
            arr[i] = elements[i];
        }
        quickSort(arr, left, right);
        return arr;
    }

    private void quickSort(int[] elements, final int left, final int right) {

        int idx = partition(elements, left, right);

        if (left < idx -1) {
            quickSort(elements, left, idx - 1);
        }

        if (idx < right){
            quickSort(elements, idx, right);
        }
    }

    private int partition(int[] elements, final int left, final int right) {
        int pivot = elements[(left + right) /2];
        int leftPos = left;
        int rightPos = right;

        while(leftPos <= rightPos){

            while(elements[leftPos] < pivot){
                leftPos++;
            }

            while(elements[rightPos] > pivot){
                rightPos--;
            }

            if (leftPos <= rightPos) {
                int temp = elements[leftPos];
                elements[leftPos] = elements[rightPos];
                elements[rightPos] = temp;
                leftPos++;
                rightPos--;
            }
        }
        return leftPos;
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
            System.out.println("Gib die Länge der Liste (und den Thread Threshold) als Argument mit.");
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
        System.out.println("Starting QuickSort with length = " + length + ", threshold = " + threshold + ", pivot = mid element");
        System.out.println();

        QuickSort sort = new QuickSort(threshold);
        stopwatch.start();
        int[] sorted = sort.sort(elements, 0, length-1);
        double sortTime = stopwatch.stop();

        // System.out.println("Unsorted List: " + Arrays.toString(elements));
        // System.out.println("Sorted List: " + Arrays.toString(sorted));
        System.out.println(String.format("Needed calculationTime: %.10fms", sortTime));
    }
}
