package org.lemonade.gui.elements;

import org.lemonade.gui.values.GuiStringValue;

import javafx.scene.control.Label;

public class GuiLabelElement extends GuiElement {

    private GuiStringValue value;
    private Label label;

    public GuiLabelElement(String labelText) {
        value = new GuiStringValue(labelText);
        label = new Label(labelText.replace("\"", ""));
    }

    @Override
    public GuiStringValue getValue() {
        return value;
    }

    @Override
    public Label getWidget() {
        return label;
    }

    // Intentionally left empty as labels are immutable
    @Override
    public void clear() {
    }

    // Intentionally left empty as labels are immutable
    @Override
    public void update() {
    }
}
