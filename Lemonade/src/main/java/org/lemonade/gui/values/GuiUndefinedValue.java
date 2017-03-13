package org.lemonade.gui.values;

import javafx.scene.control.Control;

public class GuiUndefinedValue extends GuiValue<String> {

    @Override public void update() {

    }

    @Override public Control getWidget() {
        return null;
    }

    @Override String getValue() {
        return null;
    }

    @Override void setValue(final String value) {

    }

    @Override
    public boolean isDefined() {
        return false;
    }
}
