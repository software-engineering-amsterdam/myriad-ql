package org.lemonade.gui.elements.values;

import javafx.scene.control.TextField;

public class GuiStringValue extends GuiValue<String> {

    private String value;
    private TextField textField;

    public GuiStringValue() {
        textField = new TextField();
        textField.setOnKeyReleased(e -> update());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void update() {
        setValue(textField.getText());
        System.err.println("new value = " + value);
    }

    @Override
    public TextField getWidget() {
        return textField;
    }
}
