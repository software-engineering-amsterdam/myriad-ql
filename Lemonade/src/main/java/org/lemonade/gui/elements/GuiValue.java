package org.lemonade.gui.elements;

public abstract class GuiValue<T> {

    abstract T getValue();

    abstract void setValue(T value);
}
