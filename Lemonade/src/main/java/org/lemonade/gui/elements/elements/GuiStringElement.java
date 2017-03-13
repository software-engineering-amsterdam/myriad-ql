package org.lemonade.gui.elements.elements;

import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.values.GuiStringValue;
import org.lemonade.gui.elements.values.GuiUndefinedValue;
import org.lemonade.gui.elements.values.GuiValue;

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
