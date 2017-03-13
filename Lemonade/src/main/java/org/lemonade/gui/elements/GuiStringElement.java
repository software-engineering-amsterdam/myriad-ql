package org.lemonade.gui.elements;

import org.lemonade.gui.GuiElement;
import org.lemonade.gui.values.GuiStringValue;
import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;

import javafx.scene.control.TextField;

public class GuiStringElement implements GuiElement {

    private GuiValue<?> value;
    private TextField textField;

    public GuiStringElement() {
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
            this.value = new GuiUndefinedValue();
        else if (this.value.isDefined())
            ((GuiStringValue) this.value).update(textFieldValue);
        else
            this.value = new GuiStringValue(textFieldValue);
    }

    @Override
    public TextField getWidget() {
        return textField;
    }

}
