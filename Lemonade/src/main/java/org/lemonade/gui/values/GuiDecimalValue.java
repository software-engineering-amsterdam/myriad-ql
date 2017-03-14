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
    public boolean isDecimal() {
        return true;
    }

    public GuiDecimalValue plus(GuiIntegerValue that) {
        return new GuiDecimalValue(this.getValue() + that.getValue());
    }

    public GuiDecimalValue plus(GuiDecimalValue that) {
        return new GuiDecimalValue(this.getValue() + that.getValue());
    }

    public GuiMoneyValue plus(GuiMoneyValue that) {
        return new GuiMoneyValue(this.getValue() + that.getValue());
    }

    public GuiDecimalValue minus(GuiIntegerValue that) {
        return new GuiDecimalValue(this.getValue() - that.getValue());
    }

    public GuiDecimalValue minus(final GuiDecimalValue that) {
        return new GuiDecimalValue(this.getValue() - that.getValue());
    }

    public GuiMoneyValue minus(final GuiMoneyValue that) {
        return new GuiMoneyValue(this.getValue() - that.getValue());
    }

    public GuiDecimalValue product(final GuiIntegerValue that) {
        return new GuiDecimalValue(this.getValue() * that.getValue());
    }

    public GuiDecimalValue product(final GuiDecimalValue that) {
        return new GuiDecimalValue(this.getValue() * that.getValue());
    }

    public GuiMoneyValue product(final GuiMoneyValue that) {
        return new GuiMoneyValue(this.getValue() * that.getValue());
    }

    public GuiDecimalValue divide(final GuiIntegerValue that) {
        return new GuiDecimalValue((Double) (this.getValue() / that.getValue()));
    }

    public GuiDecimalValue divide(final GuiDecimalValue that) {
        return new GuiDecimalValue(this.getValue() / that.getValue());
    }

    public GuiMoneyValue divide(final GuiMoneyValue that) {
        return new GuiMoneyValue(this.getValue() / that.getValue());
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
