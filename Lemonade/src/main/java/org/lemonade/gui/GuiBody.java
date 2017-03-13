package org.lemonade.gui;

import org.lemonade.gui.elements.GuiElement;
import org.lemonade.visitors.EvaluateVisitor;

public abstract class GuiBody {

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
