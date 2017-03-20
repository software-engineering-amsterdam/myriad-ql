package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiUndefinedValue extends GuiValue<Void> {

    @Override

    public Void getValue() {
        return null;
    }

    @Override
    public void update(final Void value) {

    }

    @Override
    public GuiValue<?> plus(GuiValue<?> that) {
        return this;
    }

    @Override
    public boolean isDefined() {
        return false;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public GuiValue<?> divide(GuiValue<?> that) {
        return this;
    }

    @Override
    public GuiValue<?> product(GuiValue<?> that) {
        return this;
    }

    @Override
    public GuiValue<?> minus(GuiValue<?> that) {
        return this;
    }

    public GuiValue<?> doAnd(GuiValue<?> that){
        return this;
    }

    public GuiValue<?> doOr(GuiValue<?> that){
        return this;
    }
}
