package org.lemonade.gui.elements;

public abstract class GuiValue<T> implements GuiElement {

    abstract T getValue();

    abstract void setValue(T value);
}
