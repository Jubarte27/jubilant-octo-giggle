package sorting.algorithms;

import sorting.drawableList.RecordingList;

public class QuickSort {
    public static <E extends Comparable<? super E>> void sort(RecordingList<E> elements) {
        quickSort(elements, 0, elements.size() - 1);
    }

    private static <E extends Comparable<? super E>> void quickSort(RecordingList<E> elements, int lo, int hi) {
        if (lo < hi) {
            int indexPivot = partition(elements, lo, hi);
            quickSort(elements, lo, indexPivot - 1);
            quickSort(elements, indexPivot + 1, hi);
        }
    }

    private static <E extends Comparable<? super E>> int partition(RecordingList<E> elements, int lo, int hi) {
        int i = lo + 1;

        while (i <= hi) {
            if (elements.compare(i, lo) <= 0) {
                i++;
            } else if (elements.compare(lo, hi) < 0) {
                hi--;
            } else {
                elements.swap(i, hi);
                i++;
                hi--;
            }
        }

        elements.swap(hi, lo);
        return hi;
    }
}
