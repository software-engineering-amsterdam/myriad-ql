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

    public GuiBooleanValue and(GuiBooleanValue that) {
        if (!that.isDefined()){
            return that;
        }
        return new GuiBooleanValue(this.value && that.getValue());
    }

    public GuiBooleanValue or(GuiBooleanValue that) {
        if (!that.isDefined()){
            return that;
        }
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
