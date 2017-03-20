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
        return that.divide(this);
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
    public GuiDecimalValue divide(GuiDecimalValue that) {
        return new GuiDecimalValue(that.getValue() / this.getValue());
    }

    @Override
    public GuiDecimalValue divide(GuiIntegerValue that) {
        return new GuiDecimalValue((Double) (that.getValue() / this.getValue()));
    }

    @Override
    public GuiMoneyValue divide(GuiMoneyValue that) {
        return new GuiMoneyValue(that.getValue() / this.getValue());
    }

    @Override
    public GuiBooleanValue gT(final GuiComparableValue<?> that) {
        return new GuiBooleanValue(this.compareTo((GuiDecimalValue) that) == 1);
    }

    @Override
    public GuiBooleanValue gTEq(final GuiComparableValue<?> that) {
        return new GuiBooleanValue(this.compareTo((GuiDecimalValue) that) >= 0);
    }

    @Override
    public GuiBooleanValue lT(final GuiComparableValue<?> that) {
        return new GuiBooleanValue(this.compareTo((GuiDecimalValue) that) == -1);
    }

    @Override
    public GuiBooleanValue lTEq(final GuiComparableValue<?> that) {
        return new GuiBooleanValue(this.compareTo((GuiDecimalValue) that) <= 0);
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
