package org.lemonade.gui.elements;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import org.lemonade.gui.values.GuiValue;

/**
 *
 */
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

    @Override
    public Control getWidget() {
        return label;
    }

    public void update(GuiValue<?> value) {
        this.value = value;
        label.setText(value.toString());
    }

    @Override
    public void update() {

    }

    @Override
    public void clear() {

    }
}
