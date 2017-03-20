package org.qls.ast.widget.defaultWidget.style;

public class FontRule extends StyleRule {
    private final String font;

    public FontRule(String font) {
        this.font = font;
    }

    public String getFont() {
        return font;
    }
}
