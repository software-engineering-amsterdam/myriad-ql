package org.lemonade.gui.values;

import org.lemonade.exceptions.NotSupportedException;
import org.lemonade.gui.GuiExpression;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public abstract class GuiValue<T> implements GuiExpression {

    public abstract T getValue();

    abstract void update(T value);

    public boolean isDefined() {
        return true;
    }

    public abstract <T> T accept(GuiExpressionVisitor<T> visitor);

    public GuiValue<?> and(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> doAnd(GuiBooleanValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> or(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> doOr(GuiBooleanValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<Boolean> eq(GuiValue<?> that) {
        return new GuiBooleanValue(that.equals(this));
    }

    public GuiValue<Boolean> nEq(GuiValue<?> that) {
        return new GuiBooleanValue(!that.equals(this));
    }

    public GuiValue<?> plus(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> min(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> prod(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> div(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> add(GuiIntegerValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> add(GuiMoneyValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> add(GuiDecimalValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> divide(GuiIntegerValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> divide(GuiMoneyValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> divide(GuiDecimalValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> product(GuiIntegerValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> product(GuiMoneyValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> product(GuiDecimalValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> minus(GuiIntegerValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> minus(GuiMoneyValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> minus(GuiDecimalValue that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> lT(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> gT(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> lTE(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> gTE(GuiValue<?> that) {
        throw new NotSupportedException();
    }
}
