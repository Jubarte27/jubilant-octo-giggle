package sorting.drawing;

public record Color(float r, float g, float b, float a) {
    public Color(float r, float g, float b) {
        this(r, g, b, 255);
    }
}
