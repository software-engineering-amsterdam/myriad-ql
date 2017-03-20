package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiMoneyValue extends GuiNumericalValue<Double> implements Comparable<GuiMoneyValue> {

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
    public GuiMoneyValue add(GuiDecimalValue that) {
        return new GuiMoneyValue(that.getValue() + this.getValue());
    }

    @Override
    public GuiMoneyValue add(GuiIntegerValue that) {
        return new GuiMoneyValue(that.getValue() + this.getValue());
    }

    @Override
    public GuiMoneyValue add(GuiMoneyValue that) {
        return new GuiMoneyValue(that.getValue() + this.getValue());
    }

    @Override
    public GuiMoneyValue minus(GuiDecimalValue that) {
        return new GuiMoneyValue(that.getValue() - this.getValue());
    }

    @Override
    public GuiMoneyValue minus(GuiIntegerValue that) {
        return new GuiMoneyValue(that.getValue() - this.getValue());
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
    public GuiMoneyValue product(GuiIntegerValue that) {
        return new GuiMoneyValue(that.getValue() * this.getValue());
    }

    @Override
    public GuiMoneyValue product(GuiMoneyValue that) {
        return new GuiMoneyValue(that.getValue() * this.getValue());
    }

    @Override
    public GuiMoneyValue divide(GuiDecimalValue that) {
        return new GuiMoneyValue(that.getValue() / this.getValue());
    }

    @Override
    public GuiMoneyValue divide(GuiIntegerValue that) {
        return new GuiMoneyValue((Double) (that.getValue() / this.getValue()));
    }

    @Override
    public GuiMoneyValue divide(GuiMoneyValue that) {
        return new GuiMoneyValue(that.getValue() / this.getValue());
    }

    @Override
    public GuiValue<?> doGt(GuiMoneyValue that) {
        return new GuiBooleanValue(that.compareTo(this) == 1);
    }

    @Override
    public GuiValue<?> doLt(GuiMoneyValue that) {
        return new GuiBooleanValue(that.compareTo(this) == -1);
    }

    @Override
    public GuiValue<?> doGtE(GuiMoneyValue that) {
        return new GuiBooleanValue(that.compareTo(this) >= 0);
    }

    @Override
    public GuiValue<?> doLtE(GuiMoneyValue that) {
        return new GuiBooleanValue(that.compareTo(this) <= 0);
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
