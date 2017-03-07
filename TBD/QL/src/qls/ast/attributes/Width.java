package qls.ast.attributes;

/**
 * Created by rico on 7-3-17.
 */
public class Width extends Attribute {
    private final int width;

    public Width(int width, int rowNumber) {
        super(rowNumber);
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
}
