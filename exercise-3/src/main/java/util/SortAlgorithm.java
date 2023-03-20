package util;

public interface SortAlgorithm {

    int[] sort(int[] elements, int left, int right);

    int getThreshold();

    ListUtil.ParallelType getType();

}
