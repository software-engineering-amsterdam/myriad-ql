package org.lemonade.gui;

import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.GuiLabelElement;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.visitors.EvaluateVisitor;
import org.lemonade.visitors.interfaces.GuiBaseElementsVisitor;

/**
 *
 */
public class GuiComputedQuestion extends GuiQuestion {

    private GuiExpression expression;

    public GuiComputedQuestion(GuiIdentifierValue identifier, GuiLabelElement labelElement, GuiElement element, GuiExpression expression) {
        super(identifier, labelElement, element);
        this.expression = expression;
    }

    public GuiExpression getExpression() {
        return expression;
    }

    @Override
    public void accept(GuiBaseElementsVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void isVisible(boolean flag) {
        super.getElement().getWidget().getParent().setVisible(flag);
        if (!flag)
            super.getElement().clear();
    }
}
