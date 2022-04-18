public class Vector2DPixel {
    public int x;
    public int y;

    public Vector2DPixel(int aX, int aY) {
        this.x = aX;
        this.y = aY;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", x, y);
    }
}
