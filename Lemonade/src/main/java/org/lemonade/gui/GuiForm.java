package org.lemonade.gui;

import java.util.List;

import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.visitors.EvaluateVisitor;

public class GuiForm extends GuiBody {

    private GuiIdentifierValue identifier;
    private List<GuiBody> bodies;

    public GuiForm(GuiIdentifierValue identifier, List<GuiBody> bodies) {
        this.identifier = identifier;
        this.bodies = bodies;
    }

    public List<GuiBody> getBodies() {
        return this.bodies;
    }

    public void accept(EvaluateVisitor visitor) {
        visitor.visit(this);
    }

    // TODO irrelevant method for form
    @Override
    public void isVisible(final boolean flag) {}
}
