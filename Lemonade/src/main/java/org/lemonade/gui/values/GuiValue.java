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

    public GuiValue<?> lT(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> gT(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> lTEq(GuiValue<?> that) {
        throw new NotSupportedException();
    }

    public GuiValue<?> gTEq(GuiValue<?> that) {
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

    public GuiValue<?> doGt(GuiIntegerValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doLt(GuiIntegerValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doGtE(GuiIntegerValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doLtE(GuiIntegerValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doGt(GuiDecimalValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doLt(GuiDecimalValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doGtE(GuiDecimalValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doLtE(GuiDecimalValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doGt(GuiMoneyValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doLt(GuiMoneyValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doGtE(GuiMoneyValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doLtE(GuiMoneyValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doGt(GuiDateValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doLt(GuiDateValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doGtE(GuiDateValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doLtE(GuiDateValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doGt(GuiStringValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doLt(GuiStringValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doGtE(GuiStringValue that) {throw new NotSupportedException(); }
    public GuiValue<?> doLtE(GuiStringValue that) {throw new NotSupportedException(); }

    // Implemented for writing to JSON
    @Override
    public String toString() {
        return getValue() != null ? getValue().toString() : "undefined";
    }
}
