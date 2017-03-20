package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiIntegerValue extends GuiNumericalValue<Integer> implements Comparable<GuiIntegerValue>{

    private Integer value;

    public GuiIntegerValue(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
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

    public GuiIntegerValue add(GuiIntegerValue that){
        return new GuiIntegerValue(that.getValue() + this.getValue());
    }

    public GuiMoneyValue add(GuiMoneyValue that){
        return new GuiMoneyValue(that.getValue() + this.getValue());
    }

    public GuiDecimalValue min(GuiDecimalValue that){
        return new GuiDecimalValue(that.getValue() - this.getValue());
    }

    public GuiIntegerValue min(GuiIntegerValue that){
        return new GuiIntegerValue(that.getValue() - this.getValue());
    }

    public GuiMoneyValue min(GuiMoneyValue that){
        return new GuiMoneyValue(that.getValue() - this.getValue());
    }

    public GuiDecimalValue prod(GuiDecimalValue that){
        return new GuiDecimalValue(that.getValue() * this.getValue());
    }

    public GuiIntegerValue prod(GuiIntegerValue that){
        return new GuiIntegerValue(that.getValue() * this.getValue());
    }

    public GuiMoneyValue prod(GuiMoneyValue that){
        return new GuiMoneyValue(that.getValue() * this.getValue());
    }

    public GuiDecimalValue div(GuiDecimalValue that){
        return new GuiDecimalValue(that.getValue() / this.getValue());
    }

    public GuiIntegerValue div(GuiIntegerValue that){
        return new GuiIntegerValue((that.getValue() / this.getValue()));
    }

    public GuiMoneyValue div(GuiMoneyValue that) {
        return new GuiMoneyValue((Double) (that.getValue() / this.getValue()));
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
