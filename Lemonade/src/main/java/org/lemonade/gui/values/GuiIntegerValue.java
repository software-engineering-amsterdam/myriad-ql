package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiIntegerValue extends GuiNumericalValue<Integer> implements Comparable<GuiIntegerValue>{

    private Integer value;

    public GuiIntegerValue(int value) {
        this.value = value;
    }

    @Override
    Integer getValue() {
        return value;
    }

    @Override
    public void update(Integer newValue) {
        this.value = newValue;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean isInteger() {
        return true;
    }

    public GuiIntegerValue plus(GuiIntegerValue that) {
        return new GuiIntegerValue(this.getValue() + that.getValue());
    }

    public GuiDecimalValue plus(GuiDecimalValue that) {
        return new GuiDecimalValue(this.getValue() + that.getValue());
    }

    public GuiMoneyValue plus(GuiMoneyValue that) {
        return new GuiMoneyValue(this.getValue() + that.getValue());
    }

    public GuiIntegerValue product(GuiIntegerValue that) {
        System.err.println(String.format("%d * %d", this.getValue(), that.getValue()));
        return new GuiIntegerValue(this.getValue() * that.getValue());
    }

    public GuiDecimalValue product(GuiDecimalValue that) {
        return new GuiDecimalValue(this.getValue() * that.getValue());
    }

    public GuiMoneyValue product(GuiMoneyValue that) {
        return new GuiMoneyValue(this.getValue() * that.getValue());
    }

    public GuiIntegerValue minus(GuiIntegerValue that) {
        return new GuiIntegerValue(this.getValue() - that.getValue());
    }

    public GuiDecimalValue minus(GuiDecimalValue that) {
        return new GuiDecimalValue(this.getValue() - that.getValue());
    }

    public GuiMoneyValue minus(GuiMoneyValue that) {
        return new GuiMoneyValue(this.getValue() - that.getValue());
    }

    public GuiIntegerValue divide(GuiIntegerValue that) {
        return new GuiIntegerValue(this.getValue() / that.getValue());
    }

    public GuiDecimalValue divide(GuiDecimalValue that) {
        return new GuiDecimalValue(this.getValue() / that.getValue());
    }

    public GuiMoneyValue divide(GuiMoneyValue that) {
        return new GuiMoneyValue(this.getValue() / that.getValue());
    }

    @Override
    public GuiBooleanValue gT(final GuiComparableValue<?> that) {
        return new GuiBooleanValue(this.compareTo((GuiIntegerValue) that) == 1);
    }

    @Override
    public GuiBooleanValue gTEq(final GuiComparableValue<?> that) {
        return new GuiBooleanValue(this.compareTo((GuiIntegerValue) that) >= 0);
    }

    @Override
    public GuiBooleanValue lT(final GuiComparableValue<?> that) {
        return new GuiBooleanValue(this.compareTo((GuiIntegerValue) that) == -1);
    }

    @Override
    public GuiBooleanValue lTEq(final GuiComparableValue<?> that) {
        return new GuiBooleanValue(this.compareTo((GuiIntegerValue) that) <= 0);
    }

    @Override
    public GuiIntegerValue neg() {
        return new GuiIntegerValue(-this.getValue());
    }

    @Override
    public String toString() {
        return Integer.toString(this.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GuiIntegerValue)) {
            return false;
        }
        GuiIntegerValue that = (GuiIntegerValue) obj;
        return this.getValue() == that.getValue();
    }

    @Override
    public int compareTo(GuiIntegerValue that) {
        if (this.getValue() < that.getValue()) {
            return -1;
        } else if (this.getValue() > that.getValue()) {
            return 1;
        } else {
            return 0;
        }
    }
}
