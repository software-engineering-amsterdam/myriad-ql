package org.qls.ast.widget.default_widget.style;

public class FontSizeRule extends StyleRule {
    private int fontSize;

    public FontSizeRule(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
