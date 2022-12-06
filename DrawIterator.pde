public class DrawIterator{
	private Drawer drawer;
	private ArrayList<Integer> vector;
	private ArrayList<Access> accesses;
	private RecordingList<Integer> recording;
	private int accessIndex = 0;

	private int delayMilliseconds = 0;

	private color swapColor = color(255, 0, 0);
	private color readColor = color(0, 0, 255);
	private color writeColor = color(0, 255, 255);
	private color compareColor = color(0, 100, 0);


	public DrawIterator(ArrayList<Integer> sortVector, RecordingList<Integer> sortRecording, Drawer dr){
		drawer = dr;
		vector = sortVector;
		recording = sortRecording;
		accesses = recording.getAccesses();
	}

	public DrawIterator(RecordingList<Integer> sortRecording){
		this((ArrayList<Integer>)sortRecording.getInitialElements(), sortRecording, new Drawer(1, 10, 300));
	}

	private void applySwap(int firstIndex, int secondIndex){
		Integer aux = vector.get(firstIndex);
		vector.set(firstIndex, vector.get(secondIndex));
		vector.set(secondIndex, aux);
	}

	private void handleSwap(Swap swapAccess){
		int firstIndex = swapAccess.firstIndex;
		int secondIndex = swapAccess.secondIndex;
		drawer.drawValueAtIndex(vector.get(firstIndex), swapColor, firstIndex);
		drawer.drawValueAtIndex(vector.get(secondIndex), swapColor, secondIndex);
		applySwap(firstIndex, secondIndex);
	}

	private void handleRead(Read readAccess){
		int index = readAccess.index;
		drawer.drawValueAtIndex(vector.get(index), readColor, index);
	}

	private void handleWrite(Write writeAccess){
		int index = writeAccess.index;
		drawer.drawValueAtIndex(vector.get(index), writeColor, index);
	}

	private void handleCompare(Compare compareAccess){
		int firstIndex = compareAccess.firstIndex;
		int secondIndex = compareAccess.secondIndex;

		drawer.drawValueAtIndex(vector.get(firstIndex), compareColor, firstIndex);
		drawer.drawValueAtIndex(vector.get(secondIndex), compareColor, secondIndex);
	}

	private void drawAccess(){
		if(accesses.size() > accessIndex){
			Access currentAccess = accesses.get(accessIndex);
			accessIndex++;
	
			if(currentAccess instanceof Compare){
				handleCompare((Compare) currentAccess);
			}else if(currentAccess instanceof Swap){
				handleSwap((Swap) currentAccess);
			}else if(currentAccess instanceof Read){
				handleRead((Read) currentAccess);
			}else if(currentAccess instanceof Write){
				handleWrite((Write)currentAccess);
			}
		}
	}

	private void printSortStats(){
    	fill(255,255,255);
		text(recording.getStats(), 0, 10);
	}

	public void iterateThroughAccesses(){
		drawer.drawVec(vector);
		drawAccess();
		printSortStats();
		delay(delayMilliseconds);
	}
}
