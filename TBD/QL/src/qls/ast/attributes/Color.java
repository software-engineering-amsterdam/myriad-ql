package qls.ast.attributes;

/**
 * Created by rico on 7-3-17.
 */
public class Color extends Attribute {
    private final String color;

    public Color(String color, int rowNumber) {
        super(rowNumber);
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
