package org.lemonade.gui.elements;

import javafx.scene.control.TextField;

public class GuiDecimalValue extends GuiValue<Double> {

    private Double value;
    private TextField textField;

    public GuiDecimalValue() {
        textField = new TextField();
    }

    @Override Double getValue() {
        return value;
    }

    @Override void setValue(final Double value) {
        this.value = value;
    }

    //TODO: verify legal double value
    @Override public void update() {
        setValue(Double.valueOf(textField.getText()));
    }
}
