package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

import java.util.Comparator;

public class GuiStringValue extends GuiValue<String> implements Comparable<GuiStringValue>{

    private String value;

    public GuiStringValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void update(String newValue) {
        this.value = newValue;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public int compareTo(GuiStringValue o) {
        return this.getValue().compareTo(o.getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GuiStringValue)) {
            return false;
        }
        GuiStringValue that = (GuiStringValue) obj;
        return this.getValue() == that.getValue();
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

    public GuiValue<?> doGt(GuiStringValue that) {
        return new GuiBooleanValue(that.compareTo(this) > 0);
    }

    public GuiValue<?> doLt(GuiStringValue that) {
        return new GuiBooleanValue(that.compareTo(this) < 0);
    }

    public GuiValue<?> doGtE(GuiStringValue that) {
        return new GuiBooleanValue(that.compareTo(this) >= 0);
    }

    public GuiValue<?> doLtE(GuiStringValue that) {
        return new GuiBooleanValue(that.compareTo(this) <= 0);
    }
}
