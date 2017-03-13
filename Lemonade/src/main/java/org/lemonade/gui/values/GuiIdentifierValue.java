package org.lemonade.gui.values;

public class GuiIdentifierValue extends GuiValue<String> {

    private String value;

    public GuiIdentifierValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void update(String value) {
        this.value = value;
    }

}
