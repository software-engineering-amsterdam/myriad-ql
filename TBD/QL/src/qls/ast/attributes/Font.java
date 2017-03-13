package qls.ast.attributes;

import qls.ast.literals.QLSString;

/**
 * Created by rico on 7-3-17.
 */
public class Font extends Attribute {
    private final QLSString font;

    public Font(QLSString font, int rowNumber) {
        super(rowNumber);
        this.font = font;
    }

    public QLSString getFont() {
        return font;
    }
}
