package org.lemonade.gui.elements;

import org.lemonade.gui.values.GuiValue;

import javafx.scene.control.Control;

public abstract class GuiElement {

    public abstract GuiValue<?> getValue();

    public abstract Control getWidget();

    public abstract void update();

    public abstract void clear();

    // Implemented for writing to JSON
    @Override
    public String toString() {
        return getValue().toString();
    }

}
