package org.lemonade.gui;

import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.GuiLabelElement;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.visitors.interfaces.GuiBaseElementsVisitor;

public abstract class GuiQuestion extends GuiBody {

    private GuiIdentifierValue identifier;
    GuiLabelElement labelElement;

    public GuiQuestion(GuiIdentifierValue identifier, GuiLabelElement labelElement) {
        this.identifier = identifier;
        this.labelElement = labelElement;
    }

    public GuiIdentifierValue getIdentifier() {
        return identifier;
    }

    public GuiLabelElement getLabelElement() {
        return labelElement;
    }

    @Override
    public boolean isQuestion() {
        return true;
    }

    public void accept(GuiBaseElementsVisitor visitor) {
        visitor.visit(this);
    }

    public abstract GuiElement getElement();

    public abstract void isVisible(boolean flag);

}
