package org.lemonade.gui.elements;

import javafx.scene.control.TextField;

public class GuiIntegerValue extends GuiValue<Integer> {

    private Integer value;
    private TextField textField;

    public GuiIntegerValue() {
        textField = new TextField();
    }

    @Override Integer getValue() {
        return value;
    }

    @Override void setValue(final Integer value) {
        this.value = value;
    }

    //TODO: verify legal int value
    @Override public void update() {
        setValue(Integer.valueOf(textField.getText()));
    }
}
