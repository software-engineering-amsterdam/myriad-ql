package org.lemonade.gui.values;

import java.time.LocalDate;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiDateValue extends GuiValue<LocalDate> implements Comparable<GuiDateValue> {

    private LocalDate value;

    public GuiDateValue(LocalDate value) {
        this.value = value;
    }

    @Override
    public LocalDate getValue() {
        return value;
    }

    @Override
    public void update(LocalDate value) {
        this.value = value;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public int compareTo(GuiDateValue that) {
        return this.getValue().compareTo(that.getValue());
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
    public GuiValue<?> doGt(GuiDateValue that) {
        return new GuiBooleanValue(that.compareTo(this) >= 1);
    }

    @Override
    public GuiValue<?> doLt(GuiDateValue that) {
        return new GuiBooleanValue(that.compareTo(this) <= -1);
    }

    @Override
    public GuiValue<?> doGtE(GuiDateValue that) {
        return new GuiBooleanValue(that.compareTo(this) >= 0);
    }

    @Override
    public GuiValue<?> doLtE(GuiDateValue that) {
        return new GuiBooleanValue(that.compareTo(this) <= 0);
    }
}
