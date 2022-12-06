public class Iterator{
	private Drawer drawer;
	private ArrayList<Integer> vector;
	private ArrayList<Access> accesses;
	private int delayMilliseconds = 10;

	public Iterator(ArrayList<Integer> sortVector, ArrayList<Access> sortAccesses, Drawer dr){
		drawer = dr;
		vector = sortVector;
		accesses = sortAccesses;
	}

	public Iterator(ArrayList<Integer> vec, ArrayList<Access> acs){
		this(vec, acs, new Drawer(6, 10, 100));
	}

	private void applySwap(int firstIndex, int secondIndex){
		Integer aux = vector.get(firstIndex);
		vector.set(firstIndex, vector.get(secondIndex));
		vector.set(secondIndex, aux);
	}

	private void handleSwap(Swap swapAccess){
		int firstIndex = swapAccess.firstIndex;
		int secondIndex = swapAccess.secondIndex;
		drawer.drawValueAtIndex(vector.get(firstIndex), color(200, 0, 0), firstIndex);
		drawer.drawValueAtIndex(vector.get(secondIndex), color(200, 0, 0), secondIndex);
		applySwap(firstIndex, secondIndex);
	}

	private void handleRead(Read readAccess){
		int index = readAccess.index;
		drawer.drawValueAtIndex(vector.get(index), color(0, 0, 200), index);
	}

	private void handleWrite(Write writeAccess){
		int index = writeAccess.index;
		drawer.drawValueAtIndex(vector.get(index), color(200, 0, 200), index);
	}

	private void handleCompare(Compare compareAccess){
		int firstIndex = compareAccess.firstIndex;
		int secondIndex = compareAccess.secondIndex;

		drawer.drawValueAtIndex(vector.get(firstIndex), color(0, 200, 0), firstIndex);
		drawer.drawValueAtIndex(vector.get(secondIndex), color(0, 200, 0), secondIndex);
	}

	private void drawAccess(){
		if(!accesses.isEmpty()){
			Access currentAccess = accesses.remove(0);
	
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

	public void iterateThroughAccesses(){
		drawer.drawVec(vector);
		drawAccess();
		delay(delayMilliseconds);
	}
}
