package org.lemonade.gui.values;

public class GuiStringValue extends GuiValue<String> {

    private String value;

    public GuiStringValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void update(String newValue) {
        this.value = newValue;
    }

}
