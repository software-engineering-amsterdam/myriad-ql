package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiUndefinedValue extends GuiValue<Void> {

    @Override
    public Void getValue() {
        return null;
    }

    @Override
    public void update(final Void value) {

    }

    @Override
    public boolean isDefined() {
        return false;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
