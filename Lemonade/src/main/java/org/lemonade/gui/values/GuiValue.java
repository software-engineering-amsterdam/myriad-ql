package org.lemonade.gui.values;

public abstract class GuiValue<T> {

    abstract T getValue();

    abstract void update(T value);

    public boolean isDefined() {
        return true;
    }

}
