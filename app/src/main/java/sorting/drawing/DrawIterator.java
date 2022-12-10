package sorting.drawing;

import processing.core.PApplet;
import sorting.drawableList.RecordingList;
import sorting.drawableList.access.*;

import java.util.ArrayList;
import java.util.Collections;

public class DrawIterator{
	private final Drawer drawer;
	private final PApplet applet;
	private final ArrayList<Integer> vector;
	private final ArrayList<Access> accesses;
	private final RecordingList<Integer> recording;
	private int accessIndex = 0;

	private final int delayMilliseconds = 0;

	private final Color swapColor = new Color(1, 0, 0);
	private final Color readColor = new Color(0, 0, 1);
	private final Color writeColor = new Color(0, 1, 1);
	private final Color compareColor = new Color(0, 0.5f, 0);

	public DrawIterator(Drawer drawer, PApplet applet, RecordingList<Integer> recording) {
		this.drawer = drawer;
		this.applet = applet;
		this.recording = recording;
		this.vector = recording.initialElements();
		this.accesses = recording.accesses();
	}

	public DrawIterator(PApplet applet, RecordingList<Integer> recording){
		this(defaultDrawer(applet, recording), applet, recording);
	}

	private static Drawer defaultDrawer(PApplet applet, RecordingList<Integer> recording) {
		int max = Collections.max(recording.initialElements());
		int min = Collections.min(recording.initialElements());
		float scaleY = applet.height / (float)(max - min);
		return new Drawer(applet, (float) applet.width / recording.size(), 0, applet.height + (float) min, 1, scaleY);
	}

	private void applySwap(int firstIndex, int secondIndex){
		Integer aux = vector.get(firstIndex);
		vector.set(firstIndex, vector.get(secondIndex));
		vector.set(secondIndex, aux);
	}

	private void handleSwap(Swap swapAccess){
		int firstIndex = swapAccess.firstIndex();
		int secondIndex = swapAccess.secondIndex();
		drawer.drawValueAtIndex(vector.get(firstIndex), swapColor, firstIndex);
		drawer.drawValueAtIndex(vector.get(secondIndex), swapColor, secondIndex);
		applySwap(firstIndex, secondIndex);
	}

	private void handleRead(Read readAccess){
		int index = readAccess.index();
		drawer.drawValueAtIndex(vector.get(index), readColor, index);
	}

	private void handleWrite(Write writeAccess){
		int index = writeAccess.index();
		drawer.drawValueAtIndex(vector.get(index), writeColor, index);
	}

	private void handleCompare(Compare compareAccess){
		int firstIndex = compareAccess.firstIndex();
		int secondIndex = compareAccess.secondIndex();

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
    	applet.fill(1,1,1);
	}

	public void iterateThroughAccesses(){
		drawer.drawVec(vector);
		drawAccess();
		printSortStats();
		applet.delay(delayMilliseconds);
	}
}
