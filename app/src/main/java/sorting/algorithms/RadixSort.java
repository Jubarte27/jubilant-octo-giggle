package sorting.algorithms;

import sorting.drawableList.RecordingList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RadixSort {
    public static void sort(RecordingList<Integer> records) {

        int[] limits = getLimits(records);
        rescale(records, limits[1]);

        for (int exponent = 0; exponent < limits[2]; ++exponent) {
            ArrayList<Queue<Integer>> buckets = IntStream
                    .range(0, 10)
                    .mapToObj(a -> new LinkedList<Integer>())
                    .collect(Collectors.toCollection(ArrayList::new));
            for (int value : records) {
                int digit = (int) (value / Math.pow(10, exponent) % 10);
                buckets.get(digit).add(value);
            }

            List<Integer> intermediates = buckets.stream().flatMap(Collection::stream).toList();

            for (int i = 0, size = intermediates.size(); i < size; ++i) {
                records.set(i, intermediates.get(i));
            }
        }

        rescale(records, -limits[1]);
    }

    private static void rescale(RecordingList<Integer> arry, int delta) {
        arry.replaceAll(integer -> integer - delta);
    }

    private static int[] getLimits(RecordingList<Integer> tlist) {

        int[] lims = new int[3];

        for (int i : tlist) {
            lims[0] = Math.max(lims[0], i);
            lims[1] = Math.min(lims[1], i);
        }
        lims[2] = (int) Math.ceil(Math.log10(lims[0] - lims[1]));

        return lims;
    }
}
