package org.lemonade.gui.values;

/**
 *
 */
public abstract class GuiNumericalValue<T> extends GuiComparableValue<T> {

    public abstract GuiNumericalValue<?> plus(GuiIntegerValue that);

    public abstract GuiNumericalValue<?> plus(GuiDecimalValue that);

    public abstract GuiNumericalValue<?> plus(GuiMoneyValue that);

    public abstract GuiNumericalValue<?> minus(GuiIntegerValue that);

    public abstract GuiNumericalValue<?> minus(GuiDecimalValue that);

    public abstract GuiNumericalValue<?> minus(GuiMoneyValue that);

    public abstract GuiNumericalValue<?> product(GuiIntegerValue that);

    public abstract GuiNumericalValue<?> product(GuiDecimalValue that);

    public abstract GuiNumericalValue<?> product(GuiMoneyValue that);

    public abstract GuiNumericalValue<?> divide(GuiIntegerValue that);

    public abstract GuiNumericalValue<?> divide(GuiDecimalValue that);

    public abstract GuiNumericalValue<?> divide(GuiMoneyValue that);

    public abstract GuiNumericalValue<?> neg();

    public boolean isInteger(){
        return false;
    }

    public boolean isDecimal(){
        return false;
    }

    public boolean isMoney(){
        return false;
    }

    public GuiNumericalValue<?> plus(final GuiNumericalValue<?> that) {
        if (that.isInteger()) {
            return this.plus((GuiIntegerValue) that);
        } else if (that.isDecimal()) {
            return this.plus((GuiDecimalValue) that);
        } else if (that.isMoney()) {
            return this.plus((GuiMoneyValue) that);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GuiNumericalValue<?> product(final GuiNumericalValue<?> that) {
        if (that.isInteger()) {
            return this.product((GuiIntegerValue) that);
        } else if (that.isDecimal()) {
            return this.product((GuiDecimalValue) that);
        } else if (that.isMoney()) {
            return this.product((GuiMoneyValue) that);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GuiNumericalValue<?> minus(final GuiNumericalValue<?> that) {
        if (that.isInteger()) {
            return this.minus((GuiIntegerValue) that);
        } else if (that.isDecimal()) {
            return this.minus((GuiDecimalValue) that);
        } else if (that.isMoney()) {
            return this.minus((GuiMoneyValue) that);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GuiNumericalValue<?> divide(final GuiNumericalValue<?> that) {
        if (that.isInteger()) {
            return this.divide((GuiIntegerValue) that);
        } else if (that.isDecimal()) {
            return this.divide((GuiDecimalValue) that);
        } else if (that.isMoney()) {
            return this.divide((GuiMoneyValue) that);

        } else {
            throw new IllegalArgumentException();
        }
    }
}
