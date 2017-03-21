package org.qls.ast.widget;

public class Radio extends YesNoWidget {
    public Radio(String yesText, String noText) {
        super(yesText, noText);
    }

    @Override
    public <T, C> T accept(WidgetVisitor<T, C> visitor, C context) {
        return visitor.visitRadio(this, context);
    }
}
