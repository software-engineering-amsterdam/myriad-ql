package org.lemonade.gui.elements;

import org.lemonade.gui.values.GuiDecimalValue;
import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;

import javafx.scene.control.TextField;

public class GuiDecimalElement extends GuiMutableElement {

    private GuiValue<?> value;
    private TextField textField;

    public GuiDecimalElement() {
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
        if (!text.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            value = new GuiUndefinedValue();
        } else if (value.isDefined()) {
            ((GuiDecimalValue) value).update(Double.parseDouble(text));
        } else
            value = new GuiDecimalValue(Double.parseDouble(text));
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
