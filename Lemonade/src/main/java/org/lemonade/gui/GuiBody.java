package org.lemonade.gui;

import org.lemonade.visitors.EvaluateVisitor;

public abstract class GuiBody {

    public boolean isConditional() {
        return false;
    }

    public boolean isQuestion() {
        return false;
    }

    public void accept(EvaluateVisitor visitor) {
        visitor.visit(this);
    }

}
