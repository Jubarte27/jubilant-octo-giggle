package sorting.algorithms;

import sorting.drawableList.RecordingList;

public class InplaceMergeSort {
    public static <E extends Comparable<? super E>> void sort(RecordingList<E> elements) {
        mergeSort(elements, 0, elements.size() - 1);
    }

    private static <E extends Comparable<? super E>> void mergeSort(RecordingList<E> elements, int ini, int end) {
        if (ini >= end) return;
        int mid = (ini + end) / 2;
        mergeSort(elements, ini, mid);
        mergeSort(elements, mid + 1, end);
        merge(elements, ini, end);
    }

    private static int nextGap(int gap) {
        if (gap <= 1)
            return 0;
        return (int) Math.ceil(gap / 2.0);
    }

    private static <E extends Comparable<? super E>> void merge(RecordingList<E> elements, int ini, int end) {
        int gap = end - ini + 1;
        for (gap = nextGap(gap); gap > 0; gap = nextGap(gap)) {
            for (int i = ini; i + gap <= end; i++) {
                int j = i + gap;
                if (elements.compare(i, j) > 0)
                    elements.swap(i, j);
            }
        }
    }
}
