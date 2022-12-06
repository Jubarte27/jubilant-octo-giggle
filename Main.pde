DrawIterator drawIterator;

void setup() { 
	size(640, 360);
	rectMode(CORNERS);
	noStroke();
	textSize(10);

	ArrayList<Integer> vector = new ArrayList<>();
	int vecSize = 400;
	randomizeVector(vector, vecSize);


  	RecordingList<Integer> recordingList = new RecordingList<>(vector);
	applySorter(recordingList);

	drawIterator = new DrawIterator(recordingList);
}

void draw(){
	background(0);
	drawIterator.iterateThroughAccesses();
}


void randomizeVector(ArrayList<Integer> vector, int vecSize ){
	for(int i = 0; i < vecSize; i++){
		vector.add(int(random(50, 200)));
  	}
}

void applySorter(RecordingList<Integer> recordingList){
	QuickSort.sort(recordingList);
	recordingList.stop();
	// print(recordingList);
}
