package org.lemonade.gui.elements.values;

import javafx.scene.control.TextField;

public class GuiDecimalValue extends GuiValue<Double> {

    private Double value;
    private TextField textField;

    public GuiDecimalValue() {
        textField = new TextField();
        textField.setOnAction(e -> update());
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(final Double value) {
        this.value = value;
    }

    //TODO: verify legal double value
    @Override
    public void update() {
        double value = validate(textField.getText());
        setValue(value);
        System.err.println("new value = " + value);
    }

    private double validate(String text) {
        if (!text.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            return 0.0;
        }
        return Double.valueOf(text);
    }

    @Override
    public TextField getWidget() {
        return textField;
    }
}
