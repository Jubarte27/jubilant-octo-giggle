package sorting;

import processing.core.PApplet;
import sorting.algorithms.*;
import sorting.drawableList.RecordingList;
import sorting.drawing.DrawIterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App extends PApplet {
    private DrawIterator drawIterator;

    public void settings() {
        int vecSize = 100;
//        size(640, 360);
        fullScreen();
        ArrayList<Integer> vector = randomLadderVector(vecSize);


        RecordingList<Integer> recordingList = new RecordingList<>(vector);
        applySorter(recordingList);

        drawIterator = new DrawIterator(this, recordingList);
    }

    public void setup() {
        rectMode(CORNERS);
        noStroke();
        textSize(10);
        colorMode(RGB, 1);
    }

    public void draw() {
        background(0);
        drawIterator.iterateThroughAccesses();
    }

    private ArrayList<Integer> randomVector(int vecSize) {
        return new Random().ints(vecSize, 50, 200).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<Integer> randomLadderVector(int vecSize) {
        ArrayList<Integer> vector = IntStream.range(0, vecSize).boxed().collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(vector);
        return vector;
    }

    private void applySorter(RecordingList<Integer> recordingList) {
        RadixSort.sort(recordingList);
        recordingList.stop();
    }
}
