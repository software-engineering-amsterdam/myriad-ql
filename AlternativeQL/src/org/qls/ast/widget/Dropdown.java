package org.qls.ast.widget;

import org.ql.ast.type.*;

public class Dropdown extends Widget {
    private final String yesText;
    private final String noText;

    public Dropdown(String yesText, String noText) {
        this.yesText = yesText;
        this.noText = noText;
    }

    public String getYesText() {
        return yesText;
    }

    public String getNoText() {
        return noText;
    }

    public boolean isCompatibleWith(BooleanType booleanType) {
        return true;
    }

    @Override
    public <T, C> T accept(WidgetVisitor<T, C> visitor, C context) {
        return visitor.visitDropdown(this, context);
    }
}
