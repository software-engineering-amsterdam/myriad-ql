package org.lemonade.gui;

import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.GuiLabelElement;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.visitors.EvaluateVisitor;

/**
 *
 */
public class GuiQuestion extends GuiBody {

    private GuiIdentifierValue identifier;
    private GuiLabelElement labelElement;
    private GuiElement element;

    public GuiQuestion(GuiIdentifierValue identifier, GuiLabelElement labelElement, GuiElement element) {
        this.identifier = identifier;
        this.labelElement = labelElement;
        this.element = element;
    }

    public GuiIdentifierValue getIdentifier() {
        return identifier;
    }

    public GuiLabelElement getLabelElement() {
        return labelElement;
    }

    public GuiElement getElement() {
        return element;
    }

    @Override
    public boolean isQuestion() {
        return true;
    }

    public void accept(EvaluateVisitor visitor) {
        visitor.visit(this);
    }

}
