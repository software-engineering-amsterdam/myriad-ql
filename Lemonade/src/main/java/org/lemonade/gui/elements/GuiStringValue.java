package org.lemonade.gui.elements;

import javafx.scene.control.TextField;

public class GuiStringValue extends GuiValue<String> {

    private String value;
    private TextField textField;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override public void update() {
        setValue(textField.getText());
    }
}
