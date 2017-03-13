package qls.ast.attributes;

import qls.ast.literals.QLSInt;

/**
 * Created by rico on 7-3-17.
 */
public class FontSize extends Attribute {
    private final QLSInt fontSize;

    public FontSize(QLSInt fontSize, int rowNumber) {
        super(rowNumber);
        this.fontSize = fontSize;
    }

    public QLSInt getFontSize() {
        return fontSize;
    }
}
