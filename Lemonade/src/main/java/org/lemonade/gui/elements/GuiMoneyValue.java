package org.lemonade.gui.elements;

import javafx.scene.control.TextField;

public class GuiMoneyValue extends GuiValue<Double> {

    private Double value;
    private TextField textField;

    public GuiMoneyValue() {
        textField = new TextField();
    }

    @Override Double getValue() {
        return value;
    }

    @Override void setValue(final Double value) {
        this.value = value;
    }

    @Override public void update() {
        setValue(Double.valueOf(textField.getText()));
    }
}
