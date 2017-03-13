package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiBooleanValue extends GuiValue<Boolean> {

    private Boolean value;

    public GuiBooleanValue() {
        this.value = false;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void update(Boolean newValue) {
        this.value = newValue;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
