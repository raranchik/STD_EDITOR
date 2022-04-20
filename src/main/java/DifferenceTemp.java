import com.fasterxml.jackson.annotation.JsonIgnore;

public class DifferenceTemp {
    @JsonIgnore
    public final String DIFFERENCE_NAME_TEMPLATE = "Difference_";
    @JsonIgnore
    public int index = 0;

    public Vector2DPixel position = new Vector2DPixel(0, 0);
    public Vector2DPixel size = new Vector2DPixel(0, 0);

    @Override
    public String toString() {
        return String.format("%s%s", DIFFERENCE_NAME_TEMPLATE, index);
    }

}
