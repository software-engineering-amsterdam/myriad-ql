package qls.ast.attributes;

import qls.ast.literals.QLSInt;

/**
 * Created by rico on 7-3-17.
 */
public class Height extends Attribute {
    private final QLSInt height;

    public Height(QLSInt height, int rowNumber) {
        super(rowNumber);
        this.height = height;
    }

    public QLSInt getHeight() {
        return height;
    }
}
