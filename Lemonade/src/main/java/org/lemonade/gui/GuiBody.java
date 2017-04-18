package org.lemonade.gui;

import org.lemonade.visitors.interfaces.GuiBaseElementsVisitor;

public abstract class GuiBody {

    public boolean isConditional() {
        return false;
    }

    public boolean isQuestion() {
        return false;
    }

    public abstract void accept(GuiBaseElementsVisitor visitor);

    public abstract void isVisible(boolean flag);

}
