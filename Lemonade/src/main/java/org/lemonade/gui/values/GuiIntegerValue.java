package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiIntegerValue extends GuiValue<Integer> {

    private Integer value;

    public GuiIntegerValue(int value) {
        this.value = value;
    }

    @Override
    Integer getValue() {
        return value;
    }

    @Override
    public void update(Integer newValue) {
        this.value = newValue;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
