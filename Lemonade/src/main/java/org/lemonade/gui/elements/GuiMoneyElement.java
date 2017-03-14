package org.lemonade.gui.elements;

import javafx.scene.control.TextField;

import org.lemonade.gui.values.GuiMoneyValue;
import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;

public class GuiMoneyElement implements GuiElement {

    private GuiValue<?> value;
    private TextField textField;

    public GuiMoneyElement() {
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
        if (!text.matches("[-+]?[0-9]*\\.?[0-9]+"))
            value = new GuiUndefinedValue();
        else if (value.isDefined())
            ((GuiMoneyValue) value).update(Double.valueOf(text));
        else
            value = new GuiMoneyValue(Double.valueOf(text));
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
