package org.lemonade.gui.elements;

public class GuiIdentifierValue extends GuiValue<String> {

    private String value;

    public GuiIdentifierValue(String value) {
        this.value = value;
    }

    @Override String getValue() {
        return value;
    }

    @Override void setValue(final String value) {}

    @Override public void update() {}
}
