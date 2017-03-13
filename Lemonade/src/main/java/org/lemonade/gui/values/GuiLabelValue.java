package org.lemonade.gui.values;

public class GuiLabelValue extends GuiValue<String> {

    private String value;

    public GuiLabelValue(String labelText) {
        this.value = labelText;
    }

    @Override
    String getValue() {
        return value;
    }

    @Override
    void update(final String value) {
        this.value = value;
    }

}
