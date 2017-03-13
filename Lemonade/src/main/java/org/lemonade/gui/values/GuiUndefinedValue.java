package org.lemonade.gui.values;

import javafx.scene.control.Control;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiUndefinedValue extends GuiValue<String> {

    @Override public void update() {

    }

    @Override public Control getWidget() {
        return null;
    }

    @Override String getValue() {
        return null;
    }

    @Override void setValue(final String value) {

    }

    @Override
    public boolean isDefined() {
        return false;
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
