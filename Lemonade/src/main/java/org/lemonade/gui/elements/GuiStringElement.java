package org.lemonade.gui.elements;

import org.lemonade.gui.values.GuiStringValue;
import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;

import javafx.scene.control.TextField;

public class GuiStringElement extends GuiElement implements GuiMutableElement {

    private GuiValue<?> value;
    private TextField textField;

    public GuiStringElement() {
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
        String textFieldValue = textField.getText();
        if (textFieldValue.equals(""))
            value = new GuiUndefinedValue();
        else if (value.isDefined())
            ((GuiStringValue) value).update(textFieldValue);
        else
            value = new GuiStringValue(textFieldValue);
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
