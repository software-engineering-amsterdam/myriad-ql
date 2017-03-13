package org.lemonade.gui.values;

import org.lemonade.gui.GuiExpression;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public abstract class GuiValue<T> implements GuiExpression{

    abstract T getValue();

    abstract void update(T value);

    public boolean isDefined() {
        return true;
    }

    public abstract <T> T accept(GuiExpressionVisitor<T> visitor);
}
