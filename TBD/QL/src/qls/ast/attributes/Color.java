package qls.ast.attributes;

import qls.ast.literals.QLSHex;

/**
 * Created by rico on 7-3-17.
 */
public class Color extends Attribute {
    private final QLSHex color;

    public Color(QLSHex color, int rowNumber) {
        super(rowNumber);
        this.color = color;
    }

    public QLSHex getColor() {
        return color;
    }
}
