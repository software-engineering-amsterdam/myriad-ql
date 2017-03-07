package org.lemonade.gui.elements;

import javafx.scene.control.Label;

public class GuiLabelValue extends GuiValue<String> {

    private String value;
    private Label label;

    public GuiLabelValue(String labelText) {
        this.label = new Label(labelText);
    }

    @Override public void update() {

    }

    @Override String getValue() {
        return label.getText();
    }

    @Override void setValue(final String value) {
    }

    @Override
    public Label getWidget() {
        return label;
    }
}
