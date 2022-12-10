package sorting.drawing;

import processing.core.PApplet;

import java.util.ArrayList;

public class Drawer { //gaveta
    private final float columnWidth;
    private final float initialX;
    private final float initialY;
    private final float scaleX;
    private final float scaleY;
    private float currentX;
    private final Color defaultColor;
    private final PApplet applet;

    public Drawer(PApplet applet, float columnWidth, float initialX, float initialY, float scaleX, float scaleY, Color defaultColor) {
        this.columnWidth = columnWidth;
        this.initialX = initialX;
        this.initialY = initialY;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.defaultColor = defaultColor;
        this.applet = applet;
    }

    public Drawer(PApplet applet, float wid, float x, float y, float scaleX, float scaleY) {
        this(applet, wid, x, y, scaleX, scaleY, new Color(1, 1, 1));
    }

    public void drawValue(Integer value) {
        drawValue(value, defaultColor, currentX);
    }

    public void drawValue(Integer value, Color col) {
        drawValue(value, col, currentX);
    }

    public void drawValue(Integer value, Color col, float positionX) {
        applet.fill(col.r(), col.r(), col.g(), col.a());
        applet.rect(positionX, initialY, positionX + (columnWidth * scaleX), initialY - (value * scaleY));
    }

    public void drawValueAtIndex(Integer value, Color col, int index) {
        drawValue(value, col, initialX + index * columnWidth);
    }

    public void drawVec(ArrayList<Integer> vec) {
        for (var i : vec) {
            drawValue(i);
            currentX += columnWidth;
        }
        currentX = initialX;
    }

    public void drawVec(ArrayList<Integer> vec, Color col) {
        for (var i : vec) {
            drawValue(i, col);
            currentX += columnWidth;
        }
        currentX = initialX;
    }
}
