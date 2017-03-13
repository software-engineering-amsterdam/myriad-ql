package org.lemonade.gui.elements.values;

import org.lemonade.gui.elements.GuiElement;

public abstract class GuiValue<T> implements GuiElement {

    abstract T getValue();

    abstract void setValue(T value);

}
