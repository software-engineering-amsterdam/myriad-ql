package org.qls.ast.widget.default_widget.style;

public class FontRule extends StyleRule {
    private final String font;

    public FontRule(String font) {
        this.font = font;
    }

    public String getFont() {
        return font;
    }
}
