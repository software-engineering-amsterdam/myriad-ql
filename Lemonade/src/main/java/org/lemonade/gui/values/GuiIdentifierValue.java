package org.lemonade.gui.values;

import javafx.scene.control.Control;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

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

    @Override public Control getWidget() {
        return null;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
