package org.lemonade.gui.elements;

import javafx.scene.control.TextField;

public class GuiMoneyValue extends GuiValue<Double> implements GuiElement {

    private Double value;
    private TextField textField;

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
