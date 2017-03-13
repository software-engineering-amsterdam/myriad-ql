package org.lemonade.gui.elements;

import javafx.scene.control.TextField;
import org.lemonade.gui.GuiElement;
import org.lemonade.gui.values.GuiDecimalValue;
import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;

public class GuiDecimalElement implements GuiElement{

    private GuiValue<?> value;
    private TextField textField;

    public GuiDecimalElement() {
        textField = new TextField();
        textField.setOnKeyReleased(e -> update());
    }

    @Override
    public GuiValue<?> getValue() {
        return value;
    }

    @Override public void update() {
        validate(textField.getText());
    }

    private void validate(String text) {
        if (!text.matches("[-+]?[0-9]*\\.?[0-9]+")) {
            this.value = new GuiUndefinedValue();
        } else if (this.value.isDefined()){
            ((GuiDecimalValue) this.value).update(Double.parseDouble(text));
        } else
            this.value = new GuiDecimalValue(Double.parseDouble(text));
    }

    @Override
    public TextField getWidget() {
        return textField;
    }
}
