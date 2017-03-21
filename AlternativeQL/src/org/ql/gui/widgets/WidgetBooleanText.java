package org.ql.gui.widgets;

public class WidgetBooleanText {
    private String yesText;
    private String noText;

    public WidgetBooleanText(String yesText, String noText) {
        this.yesText = yesText;
        this.noText = noText;
    }

    public String getYesText() {
        return yesText;
    }

    public String getNoText() {
        return noText;
    }
}
