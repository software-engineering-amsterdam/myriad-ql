package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiBooleanValue extends GuiValue<Boolean> {

    private Boolean value;

    public GuiBooleanValue() {
        this.value = false;
    }

    public GuiBooleanValue(Boolean value) {
        this.value = value;
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
    public GuiValue<?> and(GuiValue<?> that) {
        return that.doAnd(this);
    }

    public GuiValue<?> doAnd(GuiBooleanValue that ) {
        return new GuiBooleanValue(this.value && that.getValue());
    }

    public GuiValue<?> or(GuiValue<?> that) {
        return that.doOr(this);
    }

    public GuiValue<?> doOr(GuiBooleanValue that) {
        return new GuiBooleanValue(this.value || that.getValue());
    }

    public GuiBooleanValue bang() {
        return new GuiBooleanValue(!this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GuiBooleanValue)) {
            return false;
        }
        GuiBooleanValue that = (GuiBooleanValue) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
