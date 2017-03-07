package qls.ast.attributes;

/**
 * Created by rico on 7-3-17.
 */
public class FontSize extends Attribute {
    private final int fontSize;

    public FontSize(int fontSize, int rowNumber) {
        super(rowNumber);
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }
}
