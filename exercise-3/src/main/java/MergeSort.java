import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeSort {

    private final ExecutorService executorService = Executors.newFixedThreadPool(16);

    private final int length;

    private final int threshold;

    private final int[] elements;

    public MergeSort(int length, int threshold) {
        this.length = length;
        this.threshold = threshold;
        this.elements = new int[length];

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            this.elements[i] = r.nextInt(length * 1000);
        }
    }

    public int[] getElements() {
        return elements;
    }

    public void sort() {

    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Gebe die Länge der Liste (und den Thread Threshold) als Argument mit.");
            return;
        }

        int length = Integer.parseInt(args[0]);
        int threshold = args.length == 2 ? Integer.parseInt(args[1]) : Integer.MAX_VALUE;

        System.out.println();
        System.out.println("Starting MergeSort with length = " + length + ", threshold = " + threshold);
        System.out.println();

        MergeSort sort = new MergeSort(length, threshold);
        System.out.println(Arrays.toString(sort.getElements()));
        sort.sort();
        System.out.println(Arrays.toString(sort.getElements()));

    }
}
