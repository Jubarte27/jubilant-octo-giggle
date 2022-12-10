package sorting.algorithms;

import sorting.drawableList.RecordingList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RadixSort {
    private static final boolean MSD = false;
    private static final int RADIX = 2;
    private static final double LOG_RADIX = Math.log(RADIX);

    public static void sort(RecordingList<Integer> records) {
        records.stopRecording();
        Limit limit = getLimits(records);
        records.startRecording();
        if (MSD) {
            msd(records, limit, limit.maxExponent(), 0, records.size());
        } else {
            lsd(records, limit);
        }
    }

    private static void lsd(RecordingList<Integer> records, Limit limits) {
        for (int exponent = 0; exponent <= limits.maxExponent(); ++exponent) {
            sortByExponent(records, limits, exponent);
        }
    }

    private static void msd(RecordingList<Integer> records, Limit limits, int exponent, int from, int to) {
        if (exponent < 0) {
            return;
        }
        for (Queue<Integer> bucket : sortByExponent(records, limits, exponent, from, to)) {
            msd(records, limits, exponent - 1, from, from + bucket.size());
            from = from + bucket.size();
        }
    }
    private static ArrayList<Queue<Integer>> sortByExponent(RecordingList<Integer> records, Limit limits, int exponent) {
        return sortByExponent(records, limits, exponent, 0, records.size());
    }
    private static ArrayList<Queue<Integer>> sortByExponent(RecordingList<Integer> records, Limit limits, int exponent, int from, int to) {
        ArrayList<Queue<Integer>> buckets = splitInBuckets(records, limits, exponent, from, to);
        List<Integer> intermediates = buckets.stream().flatMap(Collection::stream).toList();
        for (int i = 0, size = intermediates.size(); i < size; ++i) {
            records.set(i + from, intermediates.get(i));
        }
        return buckets;
    }
    private static ArrayList<Queue<Integer>> splitInBuckets(RecordingList<Integer> records, Limit limit, int exponent) {
        return splitInBuckets(records, limit, exponent, 0, records.size());
    }
    private static ArrayList<Queue<Integer>> splitInBuckets(RecordingList<Integer> records, Limit limits, int exponent, int from, int to) {
        ArrayList<Queue<Integer>> buckets = buckets();
        for (int i = from; i < to; i++) {
            int value = records.get(i) - limits.min();
            int digit = digitAt(value, exponent);
            buckets.get(digit).add(value);
        }
        return buckets;
    }

    private static int digitAt(int value, int exponent) {
        return (int) (value / Math.pow(RADIX, exponent) % RADIX);
    }

    private static ArrayList<Queue<Integer>> buckets() {
        return IntStream
                .range(0, RADIX)
                .mapToObj(a -> new LinkedList<Integer>())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static Limit getLimits(RecordingList<Integer> records) {
        int min = records.get(0);
        int max = records.get(0);
        for (int i : records) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int maxExponent = (int) Math.ceil(log(max - min)) - 1;
        return new Limit(min, max, maxExponent);
    }

    private static double log(double logNumber) {
        return Math.log(logNumber) / LOG_RADIX;
    }

    private record Limit(int min, int max, int maxExponent) {
    }
}
