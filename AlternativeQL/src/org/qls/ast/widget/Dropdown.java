package org.qls.ast.widget;

public class Dropdown extends YesNoWidget {
    public Dropdown(String yesText, String noText) {
        super(yesText, noText);
    }

    @Override
    public <T, C> T accept(WidgetVisitor<T, C> visitor, C context) {
        return visitor.visitDropdown(this, context);
    }
}
