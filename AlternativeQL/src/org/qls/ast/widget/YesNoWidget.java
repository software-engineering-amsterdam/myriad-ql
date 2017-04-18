package org.qls.ast.widget;

import org.ql.ast.type.BooleanType;

public abstract class YesNoWidget extends Widget {
    private final String yesText;
    private final String noText;

    YesNoWidget(String yesText, String noText) {
        this.yesText = yesText;
        this.noText = noText;
    }

    public String getYesText() {
        return yesText;
    }

    public String getNoText() {
        return noText;
    }

    public boolean isCompatibleWith(BooleanType type) {
        return true;
    }
}
