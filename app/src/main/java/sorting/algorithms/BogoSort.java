package sorting.algorithms;

import com.google.common.collect.Comparators;
import sorting.drawableList.RecordingList;

import java.util.Collections;

public class BogoSort { //kkkkkkkkk

    public static <E extends Comparable<? super E>> void sort(RecordingList<E> comparable) {
        while (!isSorted(comparable)) {
            Collections.shuffle(comparable);
        }
    }

    private static <E extends Comparable<? super E>> boolean isSorted(RecordingList<E> comparable) {
        comparable.stopRecording();
        boolean inOrder = Comparators.isInOrder(comparable, Comparable::compareTo);
        comparable.startRecording();
        return inOrder;
    }
}
