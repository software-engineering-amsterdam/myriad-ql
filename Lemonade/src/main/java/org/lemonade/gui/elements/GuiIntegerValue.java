package org.lemonade.gui.elements;

import javafx.scene.control.TextField;

public class GuiIntegerValue extends GuiValue<Integer> {

    private Integer value;
    private TextField textField;

    public GuiIntegerValue() {
        textField = new TextField();
        textField.setOnAction(e -> update());
    }

    @Override Integer getValue() {
        return value;
    }

    @Override void setValue(final Integer value) {
        this.value = value;
    }

    //TODO: verify legal int value
    @Override public void update() {
        int value = validate(textField.getText());
        setValue(value);
    }

    private int validate(String text) {
        if (!text.matches("[-+]?[0-9]*")) {
            return 0;
        }
        return Integer.valueOf(text);
    }

    @Override
    public TextField getWidget() {
        return textField;
    }
}
