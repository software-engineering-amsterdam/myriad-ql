package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiMoneyValue extends GuiNumericalValue<Double> implements Comparable<GuiMoneyValue>{

    private Double value;

    public GuiMoneyValue(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void update(Double value) {
        this.value = value;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean isMoney() {
        return true;
    }

    public GuiMoneyValue plus(GuiIntegerValue that) {
        return new GuiMoneyValue(this.getValue() + that.getValue());
    }

    public GuiMoneyValue plus(GuiDecimalValue that) {
        return new GuiMoneyValue(this.getValue() + that.getValue());
    }

    public GuiMoneyValue plus(GuiMoneyValue that) {
        return new GuiMoneyValue(this.getValue() + that.getValue());
    }

    public GuiMoneyValue minus(final GuiIntegerValue that) {
        return new GuiMoneyValue(this.getValue() - that.getValue());
    }

    public GuiMoneyValue minus(final GuiDecimalValue that) {
        return new GuiMoneyValue(this.getValue() - that.getValue());
    }

    public GuiMoneyValue minus(final GuiMoneyValue that) {
        return new GuiMoneyValue(this.getValue() - that.getValue());
    }

    public GuiMoneyValue product(final GuiIntegerValue that) {
        return new GuiMoneyValue(this.getValue() * that.getValue());
    }

    public GuiMoneyValue product(final GuiDecimalValue that) {
        return new GuiMoneyValue(this.getValue() * that.getValue());
    }

    public GuiMoneyValue product(final GuiMoneyValue that) {
        return new GuiMoneyValue(this.getValue() * that.getValue());
    }

    public GuiMoneyValue divide(final GuiIntegerValue that) {
        return new GuiMoneyValue((Double) (this.getValue() / that.getValue()));
    }

    public GuiMoneyValue divide(final GuiDecimalValue that) {
        return new GuiMoneyValue(this.getValue() / that.getValue());
    }

    public GuiMoneyValue divide(final GuiMoneyValue that) {
        return new GuiMoneyValue(this.getValue() / that.getValue());
    }

    @Override
    public GuiBooleanValue gT(final GuiComparableValue<?> that) {
        return new GuiBooleanValue(this.compareTo((GuiMoneyValue) that) == 1);
    }

    @Override
    public GuiBooleanValue gTEq(final GuiComparableValue<?> that) {
        return new GuiBooleanValue(this.compareTo((GuiMoneyValue) that) >= 0);
    }

    @Override
    public GuiBooleanValue lT(final GuiComparableValue<?> that) {
        return new GuiBooleanValue(this.compareTo((GuiMoneyValue) that) == -1);
    }

    @Override
    public GuiBooleanValue lTEq(final GuiComparableValue<?> that) {
        return new GuiBooleanValue(this.compareTo((GuiMoneyValue) that) <= 0);
    }

    @Override
    public GuiMoneyValue neg() {
        return new GuiMoneyValue(-this.getValue());
    }

    @Override
    public String toString() {
        return Double.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GuiMoneyValue)) {
            return false;
        }
        GuiMoneyValue that = (GuiMoneyValue) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(GuiMoneyValue that) {
        if (this.getValue() < that.getValue()) {
            return -1;
        } else if (this.getValue() > that.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
