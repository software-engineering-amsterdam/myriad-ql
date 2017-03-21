package org.lemonade.gui.elements;

import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;

import javafx.scene.control.Control;
import javafx.scene.control.Label;

public class GuiComputedElement extends GuiElement {
    private GuiValue<?> value;
    private Label label;

    public GuiComputedElement(GuiValue<?> value) {
        this.value = value;
        label = new Label(value.toString());
    }

    @Override
    public GuiValue<?> getValue() {
        return value;
    }

    public void update(GuiValue<?> value) {
        this.value = value;
        label.setText(value.toString());
    }

    @Override
    public Label getWidget() {
        return label;
    }

    @Override
    public void clear() {
        value = new GuiUndefinedValue();
        label.setText("");
    }

}
