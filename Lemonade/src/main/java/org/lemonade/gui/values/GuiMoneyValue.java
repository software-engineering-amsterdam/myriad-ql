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

    public GuiMoneyValue add(GuiDecimalValue that){
        return new GuiMoneyValue(that.getValue() + this.getValue());
    }

    public GuiMoneyValue add(GuiIntegerValue that){
        return new GuiMoneyValue(that.getValue() + this.getValue());
    }

    public GuiMoneyValue add(GuiMoneyValue that){
        return new GuiMoneyValue(that.getValue() + this.getValue());
    }

    public GuiMoneyValue min(GuiDecimalValue that){
        return new GuiMoneyValue(that.getValue() - this.getValue());
    }

    public GuiMoneyValue min(GuiIntegerValue that){
        return new GuiMoneyValue(that.getValue() - this.getValue());
    }

    public GuiMoneyValue min(GuiMoneyValue that){
        return new GuiMoneyValue(that.getValue() - this.getValue());
    }

    public GuiDecimalValue prod(GuiDecimalValue that){
        return new GuiDecimalValue(that.getValue() * this.getValue());
    }

    public GuiMoneyValue prod(GuiIntegerValue that){
        return new GuiMoneyValue(that.getValue() * this.getValue());
    }

    public GuiMoneyValue prod(GuiMoneyValue that){
        return new GuiMoneyValue(that.getValue() * this.getValue());
    }

    public GuiMoneyValue div(GuiDecimalValue that){
        return new GuiMoneyValue(that.getValue() / this.getValue());
    }

    public GuiMoneyValue div(GuiIntegerValue that){
        return new GuiMoneyValue((Double) (that.getValue() / this.getValue()));
    }

    public GuiMoneyValue div(GuiMoneyValue that) {
        return new GuiMoneyValue(that.getValue() / this.getValue());
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
