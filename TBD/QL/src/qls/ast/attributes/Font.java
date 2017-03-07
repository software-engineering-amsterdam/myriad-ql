package qls.ast.attributes;

/**
 * Created by rico on 7-3-17.
 */
public class Font extends Attribute {
    private final String font;

    public Font(String font, int rowNumber) {
        super(rowNumber);
        this.font = font;
    }

    public String getFont() {
        return font;
    }
}
