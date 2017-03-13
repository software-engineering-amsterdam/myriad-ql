package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiDecimalValue extends GuiValue<Double> {
    private Double value;

    public GuiDecimalValue(Double value) {
        this.value = value;
    }

    @Override
    Double getValue() {
        return value;
    }

    @Override
    public void update(Double newValue) {
        this.value = newValue;
    }

    @Override
    public <T> T accept(final GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
