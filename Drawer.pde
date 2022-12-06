public class Drawer{ //gaveta
	private int columnWidth, initialX, initialY;
	private int currentX;
	private color defaultColor;

	public Drawer(int wid, int x, int y, color defaultCol){
		columnWidth = wid;
		initialX = x;
		initialY = y;
		defaultColor = defaultCol;
		currentX = initialX;
	}

	public Drawer(int wid, int x, int y){
		this(wid, x, y, color(255, 255, 255));
	}

	public void drawValue(Integer value){
		drawValue(value, defaultColor, currentX);
	}

	public void drawValue(Integer value, color col){
		drawValue(value, col, currentX);
	}

	public void drawValue(Integer value, color col, int positionX){
		fill(col);
		rect(positionX, initialY, positionX + columnWidth, initialY - value);
	}

	public void drawValueAtIndex(Integer value, color col, int index){
		drawValue(value, col, initialX + index * columnWidth);
	}

	public void drawVec(ArrayList<Integer> vec){
		for(var i : vec){
			drawValue(i);
			currentX += columnWidth;
		}
		currentX = initialX;
	}

	public void drawVec(ArrayList<Integer> vec, color col){
		for(var i : vec){
			drawValue(i, col);
			currentX += columnWidth;
		}
		currentX = initialX;
	}
}
