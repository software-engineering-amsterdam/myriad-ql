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
        double value = validate(textField.getText());
        setValue(value);
    }

    private double validate(String text) {
        if (!text.matches("[-+]?[0-9]*\\.?[0-9]+")){
            return 0.0;
        }
        return Double.valueOf(text);
    }
}
