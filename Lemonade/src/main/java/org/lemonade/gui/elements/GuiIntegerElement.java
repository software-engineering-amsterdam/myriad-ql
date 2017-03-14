package org.lemonade.gui.elements;

import javafx.scene.control.TextField;

import org.lemonade.gui.values.GuiIntegerValue;
import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;

public class GuiIntegerElement implements GuiElement {

    private GuiValue<?> value;
    private TextField textField;

    public GuiIntegerElement() {
        value = new GuiUndefinedValue();
        textField = new TextField();
        textField.setOnKeyReleased(e -> update());
    }

    @Override
    public GuiValue<?> getValue() {
        return value;
    }

    @Override
    public void update() {
        validate(textField.getText());
    }

    private void validate(String text) {
        if (!text.matches("[-+]?[0-9]+")) {
            value = new GuiUndefinedValue();
        } else if (value.isDefined()) {
            ((GuiIntegerValue) value).update(Integer.parseInt(text));
        } else
            value = new GuiIntegerValue(Integer.parseInt(text));
    }

    @Override
    public void clear() {
        value = new GuiUndefinedValue();
        textField.setText("");
    }

    @Override
    public TextField getWidget() {
        return textField;
    }
}
