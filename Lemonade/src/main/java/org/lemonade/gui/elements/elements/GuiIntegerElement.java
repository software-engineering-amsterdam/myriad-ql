package org.lemonade.gui.elements.elements;

import javafx.scene.control.TextField;
import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.values.GuiIntegerValue;
import org.lemonade.gui.elements.values.GuiUndefinedValue;
import org.lemonade.gui.elements.values.GuiValue;

public class GuiIntegerElement implements GuiElement{

    private GuiValue<?> value;
    private TextField textField;

    public GuiIntegerElement() {
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
        if (!text.matches("[-+]?[0-9]*")) {
            this.value = new GuiUndefinedValue();
        } else if (this.value.isDefined()){
            ((GuiIntegerValue) this.value).update(Integer.parseInt(text));
        } else
            this.value = new GuiIntegerValue(Integer.parseInt(text));
    }

    @Override
    public TextField getWidget() {
        return textField;
    }
}
