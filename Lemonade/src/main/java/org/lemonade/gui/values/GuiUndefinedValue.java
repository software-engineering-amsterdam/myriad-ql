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
    public boolean isDefined() {
        return false;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public GuiValue<?> plus(GuiValue<?> that) {
        return this;
    }

    @Override
    public GuiValue<?> div(GuiValue<?> that) {
        return this;
    }

    @Override
    public GuiValue<?> prod(GuiValue<?> that) {
        return this;
    }

    @Override
    public GuiValue<?> min(GuiValue<?> that) {
        return this;
    }

    @Override
    public GuiValue<?> add(final GuiIntegerValue that) {
        return this;
    }

    @Override
    public GuiValue<?> add(final GuiMoneyValue that) {
        return this;
    }

    @Override
    public GuiValue<?> add(final GuiDecimalValue that) {
        return this;
    }

    @Override
    public GuiValue<?> divide(final GuiIntegerValue that) {
        return this;
    }

    @Override
    public GuiValue<?> divide(final GuiMoneyValue that) {
        return this;
    }

    @Override
    public GuiValue<?> divide(final GuiDecimalValue that) {
        return this;
    }

    @Override
    public GuiValue<?> product(final GuiIntegerValue that) {
        return this;
    }

    @Override
    public GuiValue<?> product(final GuiMoneyValue that) {
        return this;
    }

    @Override
    public GuiValue<?> product(final GuiDecimalValue that) {
        return this;
    }

    @Override
    public GuiValue<?> minus(final GuiIntegerValue that) {
        return this;
    }

    @Override
    public GuiValue<?> minus(final GuiMoneyValue that) {
        return this;
    }

    @Override
    public GuiValue<?> minus(final GuiDecimalValue that) {
        return this;
    }

    public GuiValue<?> doAnd(GuiValue<?> that) {
        return this;
    }

    public GuiValue<?> doOr(GuiValue<?> that) {
        return this;
    }
}
