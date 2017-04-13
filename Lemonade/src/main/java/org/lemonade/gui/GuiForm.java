package org.lemonade.gui;

import java.util.List;

import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.visitors.interfaces.GuiBaseElementsVisitor;

public class GuiForm extends GuiBody {

    private GuiIdentifierValue identifier;
    private List<GuiBody> bodies;

    public GuiForm(GuiIdentifierValue identifier, List<GuiBody> bodies) {
        this.identifier = identifier;
        this.bodies = bodies;
    }

    public GuiIdentifierValue getIdentifier() {
        return identifier;
    }

    public List<GuiBody> getBodies() {
        return this.bodies;
    }

    public void accept(GuiBaseElementsVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void isVisible(final boolean flag) {
    }
}
