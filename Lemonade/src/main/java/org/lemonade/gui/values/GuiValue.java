package org.lemonade.gui.values;

import org.lemonade.exceptions.NotSupportedException;
import org.lemonade.gui.GuiExpression;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    public GuiValue<?> add(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> divide(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> product(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> minus(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> lT(GuiValue<?> that ) {
        throw new NotSupportedException();
    }

    public GuiValue<?> gT(GuiValue<?> that ) {
        throw new NotSupportedException();
    }

    public GuiValue<?> lTE(GuiValue<?> that ) {
        throw new NotSupportedException();
    }

    public GuiValue<?> gTE(GuiValue<?> that ) {
        throw new NotSupportedException();
    }
}
