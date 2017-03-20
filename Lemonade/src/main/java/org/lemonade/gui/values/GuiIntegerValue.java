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
    public GuiDecimalValue add(GuiDecimalValue that){
        return new GuiDecimalValue(that.getValue() + this.getValue());
    }

    @Override
    public GuiIntegerValue add(GuiIntegerValue that){
        return new GuiIntegerValue(that.getValue() + this.getValue());
    }

    @Override
    public GuiMoneyValue add(GuiMoneyValue that){
        return new GuiMoneyValue(that.getValue() + this.getValue());
    }

    @Override
    public GuiDecimalValue minus(GuiDecimalValue that){
        return new GuiDecimalValue(that.getValue() - this.getValue());
    }

    @Override
    public GuiIntegerValue minus(GuiIntegerValue that){
        return new GuiIntegerValue(that.getValue() - this.getValue());
    }

    @Override
    public GuiMoneyValue minus(GuiMoneyValue that){
        return new GuiMoneyValue(that.getValue() - this.getValue());
    }

    @Override
    public GuiDecimalValue product(GuiDecimalValue that){
        return new GuiDecimalValue(that.getValue() * this.getValue());
    }

    @Override
    public GuiIntegerValue product(GuiIntegerValue that){
        return new GuiIntegerValue(that.getValue() * this.getValue());
    }

    @Override
    public GuiMoneyValue product(GuiMoneyValue that){
        return new GuiMoneyValue(that.getValue() * this.getValue());
    }

    @Override
    public GuiDecimalValue divide(GuiDecimalValue that){
        return new GuiDecimalValue(that.getValue() / this.getValue());
    }

    @Override
    public GuiIntegerValue divide(GuiIntegerValue that){
        return new GuiIntegerValue((that.getValue() / this.getValue()));
    }

    @Override
    public GuiMoneyValue divide(GuiMoneyValue that) {
        return new GuiMoneyValue((Double) (that.getValue() / this.getValue()));
    }

    @Override
    public GuiValue<?> doGt(GuiIntegerValue that) {
        return new GuiBooleanValue(that.compareTo(this) == 1);
    }

    @Override
    public GuiValue<?> doLt(GuiIntegerValue that) {
        return new GuiBooleanValue(that.compareTo(this) == -1);
    }

    @Override
    public GuiValue<?> doGtE(GuiIntegerValue that) {
        return new GuiBooleanValue(that.compareTo(this) >= 0);
    }

    @Override
    public GuiValue<?> doLtE(GuiIntegerValue that) {
        return new GuiBooleanValue(that.compareTo(this) <= 0);
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
