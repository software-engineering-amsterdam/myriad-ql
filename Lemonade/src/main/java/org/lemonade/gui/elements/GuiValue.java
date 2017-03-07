package org.lemonade.gui.elements;

import javafx.scene.control.Control;

public abstract class GuiValue<T> implements GuiElement {

    abstract T getValue();

    abstract void setValue(T value);

}
