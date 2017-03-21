package org.lemonade.gui.elements;

import org.lemonade.gui.values.GuiValue;

public abstract class GuiElement implements GuiWidgetElement {

    public abstract GuiValue<?> getValue();

    // Implemented for writing to JSON
    @Override
    public String toString() {
        return getValue().toString();
    }

}
