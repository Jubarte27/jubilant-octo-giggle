ArrayList<Access> accesses;
RecordingList<Integer> recordingList;

int vecSize = 100;
ArrayList<Integer> vector = new ArrayList<>();

Iterator iterator;

void setup() { 
	size(500,500);
	rectMode(CORNERS);
	strokeWeight(1);
	textSize(10);
	//noStroke();

	randomizeVector();
	applySorter();

	iterator = new Iterator(vector, recordingList);
}

void draw(){
	background(0);
	iterator.iterateThroughAccesses();
}


public void randomizeVector(){
	for(int i = 0; i < vecSize; i++){
		vector.add(int(random(50, 200)));
  	}
}

void applySorter(){
	recordingList = new RecordingList<>(vector);
	QuickSort.sort(recordingList);
	recordingList.stop();
	// print(recordingList);
}
