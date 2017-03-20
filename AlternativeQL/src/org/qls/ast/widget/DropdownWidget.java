package org.qls.ast.widget;

public class DropdownWidget extends Widget {
    private String yesText;
    private String noText;

    public DropdownWidget(String yesText, String noText) {
        this.yesText = yesText;
        this.noText = noText;
    }

    public String getYesText() {
        return yesText;
    }

    public void setYesText(String yesText) {
        this.yesText = yesText;
    }

    public String getNoText() {
        return noText;
    }

    public void setNoText(String noText) {
        this.noText = noText;
    }
}
