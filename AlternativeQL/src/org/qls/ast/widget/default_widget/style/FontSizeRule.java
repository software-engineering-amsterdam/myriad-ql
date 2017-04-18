package org.qls.ast.widget.default_widget.style;

public class FontSizeRule extends StyleRule {
    private final int fontSize;

    public FontSizeRule(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }
}
