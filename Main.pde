ArrayList<Access> accesses;

int vecSize = 50;
ArrayList<Integer> vector = new ArrayList<>();

Iterator iterator;

void setup() {
	size(500,500);
	rectMode(CORNERS);
	strokeWeight(1);

	randomizeVector(vector, vecSize);
	accesses = applySorter(vector);
	iterator = new Iterator(vector, accesses);
}

public void randomizeVector(ArrayList<Integer> vec, int size){
	for(int i = 0; i < size; i++){
		vec.add(int(random(10,50)));
  	}
}

ArrayList<Access> applySorter(ArrayList<Integer> vec){
	RecordingList<Integer> recordingList = new RecordingList<>(vec);
	QuickSort.sort(recordingList);
	recordingList.stop();
	// print(recordingList);
  	return recordingList.getAccesses();
}

void draw(){
	background(0);
	iterator.iterateThroughAccesses();
}
