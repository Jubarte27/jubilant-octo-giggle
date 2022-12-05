import drawableList.RecordingList;
import sorting.BubbleSort;
import sorting.InsertionSort;

public class Main {
    public static void main(String[] args) {
        RecordingList<Integer> recordingList = new RecordingList<>(6,2,1,6,9,8,4,3);

        InsertionSort.sort(recordingList);
//        BubbleSort.sort(recordingList);

        recordingList.stop();

        System.out.println(recordingList);
    }
}
