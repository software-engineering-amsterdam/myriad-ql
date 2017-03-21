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
}
