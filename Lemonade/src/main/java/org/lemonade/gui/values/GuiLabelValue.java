package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiLabelValue extends GuiValue<String> {

    private String value;

    public GuiLabelValue(String labelText) {
        this.value = labelText;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    void update(final String value) {
        this.value = value;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
