package qls.ast.attributes;

import qls.ast.literals.QLSInt;

/**
 * Created by rico on 7-3-17.
 */
public class Width extends Attribute {
    private final QLSInt width;

    public Width(QLSInt width, int rowNumber) {
        super(rowNumber);
        this.width = width;
    }

    public QLSInt getWidth() {
        return width;
    }
}
