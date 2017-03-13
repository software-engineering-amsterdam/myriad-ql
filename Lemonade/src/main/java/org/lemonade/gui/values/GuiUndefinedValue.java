package org.lemonade.gui.values;

public class GuiUndefinedValue extends GuiValue<Void> {

    @Override
    public Void getValue() {
        return null;
    }

    @Override
    public void update(final Void value) {

    }

    @Override
    public boolean isDefined() {
        return false;
    }

}
