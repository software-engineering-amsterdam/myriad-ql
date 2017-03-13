package org.lemonade.gui.elements.values;

public class GuiBooleanValue extends GuiValue<Boolean> {

    private Boolean value;

    public GuiBooleanValue() {
        this.value = false;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void update(Boolean newValue) {
        this.value = newValue;
    }
}
