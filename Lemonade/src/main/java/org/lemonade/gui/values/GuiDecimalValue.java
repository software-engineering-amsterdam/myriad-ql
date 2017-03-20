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

    public GuiNumericalValue<?> plus(GuiNumericalValue<?> that) {
        return that.add(this);
    }

    public GuiNumericalValue<?> minus(GuiNumericalValue<?> that) {
        return that.min(this);
    }

    public GuiNumericalValue<?> product(GuiNumericalValue<?> that) {
        return that.prod(this);
    }

    public GuiNumericalValue<?> divide(GuiNumericalValue<?> that) {
        return that.div(this);
    }

    public GuiDecimalValue add(GuiDecimalValue that){
        return new GuiDecimalValue(that.getValue() + this.getValue());
    }

    public GuiDecimalValue add(GuiIntegerValue that){
        return new GuiDecimalValue(that.getValue() + this.getValue());
    }

    public GuiMoneyValue add(GuiMoneyValue that){
        return new GuiMoneyValue(that.getValue() + this.getValue());
    }

    public GuiDecimalValue min(GuiDecimalValue that){
        return new GuiDecimalValue(that.getValue() - this.getValue());
    }

    public GuiDecimalValue min(GuiIntegerValue that){
        return new GuiDecimalValue(that.getValue() - this.getValue());
    }

    public GuiMoneyValue min(GuiMoneyValue that){
        return new GuiMoneyValue(that.getValue() - this.getValue());
    }

    public GuiDecimalValue prod(GuiDecimalValue that){
        return new GuiDecimalValue(that.getValue() * this.getValue());
    }

    public GuiDecimalValue prod(GuiIntegerValue that){
        return new GuiDecimalValue(that.getValue() * this.getValue());
    }

    public GuiMoneyValue prod(GuiMoneyValue that){
        return new GuiMoneyValue(that.getValue() * this.getValue());
    }

    public GuiDecimalValue div(GuiDecimalValue that){
        return new GuiDecimalValue(that.getValue() / this.getValue());
    }

    public GuiDecimalValue div(GuiIntegerValue that){
        return new GuiDecimalValue((Double) (that.getValue() / this.getValue()));
    }

    public GuiMoneyValue div(GuiMoneyValue that) {
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
