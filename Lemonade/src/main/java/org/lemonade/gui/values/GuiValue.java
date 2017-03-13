package org.lemonade.gui.values;

import org.lemonade.gui.GuiExpression;

public abstract class GuiValue<T> implements GuiExpression{

    abstract T getValue();

    abstract void update(T value);

    public boolean isDefined() {
        return true;
    }

}
