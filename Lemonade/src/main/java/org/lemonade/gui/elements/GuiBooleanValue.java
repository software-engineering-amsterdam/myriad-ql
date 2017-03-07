package org.lemonade.gui.elements;

import javafx.scene.control.CheckBox;

public class GuiBooleanValue extends GuiValue<Boolean> implements GuiElement {

    private Boolean value;
    private CheckBox checkBox;

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(Boolean value) {
        this.value =  value;
    }

    @Override public void update() {
        setValue(checkBox.isSelected());
    }
}
