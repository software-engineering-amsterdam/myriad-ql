package org.lemonade.gui.elements.values;

import javafx.scene.control.Control;

public class GuiIdentifierValue extends GuiValue<String> {

    private String value;

    public GuiIdentifierValue(String value) {
        this.value = value;
    }

    @Override String getValue() {
        return value;
    }

    @Override void setValue(final String value) {}

    @Override public void update() {}

    @Override public Control getWidget() {
        return null;
    }
}
