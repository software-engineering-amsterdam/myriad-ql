package org.lemonade.gui.elements;

import org.lemonade.gui.values.GuiBooleanValue;
import org.lemonade.gui.values.GuiValue;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;

public class GuiBooleanElement implements GuiElement {

    private GuiValue<Boolean> value;
    private CheckBox checkBox;

    public GuiBooleanElement() {
        value = new GuiBooleanValue();
        checkBox = new CheckBox();
        checkBox.setOnAction(e -> update());
    }

    @Override
    public GuiValue<Boolean> getValue() {
        return value;
    }

    @Override
    public void update() {
        ((GuiBooleanValue) value).update(checkBox.isSelected());
    }

    @Override
    public Control getWidget() {
        return checkBox;
    }

}
