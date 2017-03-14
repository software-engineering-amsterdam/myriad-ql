package org.lemonade.gui.values;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiLabelValue extends GuiValue<String> {

    private String value;

    public GuiLabelValue(String labelText) {
        this.value = labelText;
    }

    @Override
<<<<<<< Updated upstream
    String getValue() {
        return value;
=======
    public void update() {

    }

    @Override
    String getValue() {
        return label.getText();
    }

    @Override
    void setValue(final String value) {
>>>>>>> Stashed changes
    }

    @Override
    void update(final String value) {
        this.value = value;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
