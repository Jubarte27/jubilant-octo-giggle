package sorting;

import drawableList.RecordingList;

public class InsertionSort {
    public static <E extends Comparable<? super E>> void sort(RecordingList<E> elements){
        for(int i = 1; i < elements.size(); i++){
            for (int j = i - 1; j >= 0; j--) {
                if (elements.compare(j, j + 1) <= 0) {
                    break;
                }
                elements.swap(j, j + 1);
            }
        }
    }
}
