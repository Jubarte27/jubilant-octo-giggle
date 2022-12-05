package sorting;

import drawableList.RecordingList;

public class BubbleSort {
    public static <E extends Comparable<? super E>> void sort(RecordingList<E> comparable) {
        boolean changed;
        do {
            changed = false;
            for (int i = 0; i < comparable.size() - 1; i++) {
                if (comparable.compare(i, i + 1) > 0) {
                    comparable.swap(i, i + 1);
                    changed = true;
                }
            }
        } while (changed);
    }
}
