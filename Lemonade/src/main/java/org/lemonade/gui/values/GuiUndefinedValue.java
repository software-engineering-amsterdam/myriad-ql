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
    public GuiValue<?> lT(GuiValue<?> that) {
        return this;
    }

    @Override
    public GuiValue<?> gT(GuiValue<?> that) {
        return this;
    }

    @Override
    public GuiValue<?> lTEq(GuiValue<?> that) {
        return this;
    }

    @Override
    public GuiValue<?> gTEq(GuiValue<?> that) {
        return this;
    }

    @Override
    public GuiValue<?> and(GuiValue<?> that) {
        return this;
    }

    @Override
    public GuiValue<?> or(GuiValue<?> that) {
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

    @Override
    public GuiValue<?> doGt(GuiIntegerValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doLt(GuiIntegerValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doGtE(GuiIntegerValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doLtE(GuiIntegerValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doGt(GuiDecimalValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doLt(GuiDecimalValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doGtE(GuiDecimalValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doLtE(GuiDecimalValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doGt(GuiMoneyValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doLt(GuiMoneyValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doGtE(GuiMoneyValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doLtE(GuiMoneyValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doGt(GuiDateValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doLt(GuiDateValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doGtE(GuiDateValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doLtE(GuiDateValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doGt(GuiStringValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doLt(GuiStringValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doGtE(GuiStringValue that) {
        return this;
    }

    @Override
    public GuiValue<?> doLtE(GuiStringValue that) {
        return this;
    }

    public GuiValue<?> doAnd(GuiBooleanValue that) {
        return this;
    }

    public GuiValue<?> doOr(GuiBooleanValue that) {
        return this;
    }

    @Override
    public GuiValue<?> neg() {
        return this;
    }

    @Override
    public GuiValue<?> bang() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GuiUndefinedValue)) {
            return false;
        }
        GuiUndefinedValue that = (GuiUndefinedValue) obj;
        return this.getValue() == that.getValue();
    }
}
