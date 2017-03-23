package org.qls.ast.widget.default_widget.style;

public class ColorRule extends StyleRule {
    private final String hexCode;

    public ColorRule(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getHexCode() {
        return hexCode;
    }
}
