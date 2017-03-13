package org.lemonade.gui.elements.elements;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.values.GuiBooleanValue;
import org.lemonade.gui.elements.values.GuiValue;

public class GuiBooleanElement implements GuiElement{

    private GuiValue<Boolean> value;
    private CheckBox checkBox;

    public GuiBooleanElement() {
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
