package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiDecimalValue extends GuiNumericalValue<Double> implements Comparable<GuiDecimalValue> {
    private Double value;

    public GuiDecimalValue(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void update(Double newValue) {
        this.value = newValue;
    }

    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public GuiValue<?> plus(GuiValue<?> that) {
        return that.add(this);
    }

    @Override
    public GuiValue<?> min(GuiValue<?> that) {
        return that.minus(this);
    }

    @Override
    public GuiValue<?> prod(GuiValue<?> that) {
        return that.product(this);
    }

    @Override
    public GuiValue<?> div(GuiValue<?> that) {
        if (!(this.getValue() == 0.0)) {
            return that.divide(this);
        }
        return new GuiUndefinedValue();
    }

    @Override
    public GuiValue<?> lT(GuiValue<?> that) {
        return that.doLt(this);
    }

    @Override
    public GuiValue<?> gT(GuiValue<?> that) {
        return that.doGt(this);
    }

    @Override
    public GuiValue<?> lTEq(GuiValue<?> that) {
        return that.doLtE(this);
    }

    @Override
    public GuiValue<?> gTEq(GuiValue<?> that) {
        return that.doGtE(this);
    }

    @Override
    public GuiDecimalValue add(GuiDecimalValue that) {
        return new GuiDecimalValue(that.getValue() + this.getValue());
    }

    @Override
    public GuiDecimalValue add(GuiIntegerValue that) {
        return new GuiDecimalValue(that.getValue() + this.getValue());
    }

    @Override
    public GuiMoneyValue add(GuiMoneyValue that) {
        return new GuiMoneyValue(that.getValue() + this.getValue());
    }

    @Override
    public GuiDecimalValue minus(GuiDecimalValue that) {
        return new GuiDecimalValue(that.getValue() - this.getValue());
    }

    @Override
    public GuiDecimalValue minus(GuiIntegerValue that) {
        return new GuiDecimalValue(that.getValue() - this.getValue());
    }

    @Override
    public GuiMoneyValue minus(GuiMoneyValue that) {
        return new GuiMoneyValue(that.getValue() - this.getValue());
    }

    @Override
    public GuiDecimalValue product(GuiDecimalValue that) {
        return new GuiDecimalValue(that.getValue() * this.getValue());
    }

    @Override
    public GuiDecimalValue product(GuiIntegerValue that) {
        return new GuiDecimalValue(that.getValue() * this.getValue());
    }

    @Override
    public GuiMoneyValue product(GuiMoneyValue that) {
        return new GuiMoneyValue(that.getValue() * this.getValue());
    }

    @Override
    public GuiValue<?> divide(GuiDecimalValue that){
        if (!(this.getValue() == 0.0)) {
            return new GuiDecimalValue(that.getValue() / this.getValue());
        }
        return new GuiUndefinedValue();
    }

    @Override
    public GuiValue<?> divide(GuiIntegerValue that){
        if (!(this.getValue() == 0.0)) {
            return new GuiDecimalValue((Double)(that.getValue() / this.getValue()));
        }
        return new GuiUndefinedValue();
    }

    @Override
    public GuiValue<?> divide(GuiMoneyValue that) {
        if (!(this.getValue() == 0.0)) {
            return new GuiMoneyValue(that.getValue() / this.getValue());
        }
        return new GuiUndefinedValue();
    }

    @Override
    public GuiValue<?> doGt(GuiDecimalValue that) {
        return new GuiBooleanValue(that.compareTo(this) == 1);
    }

    @Override
    public GuiValue<?> doLt(GuiDecimalValue that) {
        return new GuiBooleanValue(that.compareTo(this) == -1);
    }

    @Override
    public GuiValue<?> doGtE(GuiDecimalValue that) {
        return new GuiBooleanValue(that.compareTo(this) >= 0);
    }

    @Override
    public GuiValue<?> doLtE(GuiDecimalValue that) {
        return new GuiBooleanValue(that.compareTo(this) <= 0);
    }
    @Override
    public GuiDecimalValue neg() {
        return new GuiDecimalValue(-this.getValue());
    }

    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GuiDecimalValue)) {
            return false;
        }
        GuiDecimalValue that = (GuiDecimalValue) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(GuiDecimalValue that) {
        if (this.getValue() < that.getValue()) {
            return -1;
        } else if (this.getValue() > that.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
