package org.lemonade.gui.elements;

public class GuiIdentifierValue extends GuiValue<String> {

    private String value;

    @Override String getValue() {
        return value;
    }

    @Override void setValue(final String value) {}
}
