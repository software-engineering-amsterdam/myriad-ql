package org.qls.ast.widget.default_widget.style;

public class WidthRule extends StyleRule {
    private int width;

    public WidthRule(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
