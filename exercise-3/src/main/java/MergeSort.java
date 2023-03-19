import java.util.Random;

public class MergeSort {

    private final int length;

    private final int threshold;

    private final int[] elements;

    public MergeSort(int length, int threshold) {
        this.length = length;
        this.threshold = threshold;
        this.elements = new int[length];

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            this.elements[i] = r.nextInt();
        }
    }

    public int[] getElements() {
        return elements;
    }

    public void sort() {

    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Gebe die Länge der Liste und den Thread Threshold als Argument mit.");
        }

        int length = Integer.parseInt(args[0]);
        int threshold = Integer.parseInt(args[1]);

        MergeSort sort = new MergeSort(length, threshold);
        sort.sort();

    }
}
