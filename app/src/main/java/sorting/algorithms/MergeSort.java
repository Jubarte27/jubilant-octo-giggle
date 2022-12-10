package sorting.algorithms;

import sorting.drawableList.RecordingList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MergeSort {
    public static <E extends Comparable<? super E>> void sort(RecordingList<E> records) {
        mergeSort(records, 0, records);
    }

    private static <E extends Comparable<? super E>> List<E> mergeSort(List<E> m, int fromIndex, RecordingList<E> records) {
        if (m.size() == 1) {
            records.set(fromIndex, m.get(0));
            return m;
        } else if (m.size() < 1) {
            return m;
        }

        int middle = (m.size() / 2);
        List<E> left = m.subList(0, middle);
        List<E> right = m.subList(middle , m.size());

        left = mergeSort(left, fromIndex, records);
        right = mergeSort(right, fromIndex + middle , records);
        List<E> result = merge(left, right);

        for (int i = 0; i < result.size(); i++) {
            records.set(i + fromIndex, result.get(i));
        }

        return result;
    }

    private static <E extends Comparable<? super E>> List<E> merge(List<E> left, List<E> right) {
        List<E> result = new ArrayList<E>();
        Iterator<E> it1 = left.iterator();
        Iterator<E> it2 = right.iterator();

        E x = it1.next();
        E y = it2.next();
        while (true) {
            //change the direction of this comparison to change the direction of the sort
            if (x.compareTo(y) <= 0) {
                result.add(x);
                if (it1.hasNext()) {
                    x = it1.next();
                } else {
                    result.add(y);
                    while (it2.hasNext()) {
                        result.add(it2.next());
                    }
                    break;
                }
            } else {
                result.add(y);
                if (it2.hasNext()) {
                    y = it2.next();
                } else {
                    result.add(x);
                    while (it1.hasNext()) {
                        result.add(it1.next());
                    }
                    break;
                }
            }
        }
        return result;
    }
}
