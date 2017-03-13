package org.lemonade.gui.elements;

import org.lemonade.visitors.EvaluateVisitor;

public abstract class GuiBody implements GuiElement {

    public boolean isConditional() {
        return false;
    }

    public boolean isQuestion() {
        return false;
    }

    public GuiBody accept(EvaluateVisitor visitor) {
        return visitor.visit(this);
    }
}
