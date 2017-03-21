package org.lemonade.gui;

import org.lemonade.gui.elements.GuiComputedElement;
import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.GuiLabelElement;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.visitors.interfaces.GuiBaseElementsVisitor;

/**
 *
 */
public class GuiComputedQuestion extends GuiQuestion {

    private GuiComputedElement valueElement;
    private GuiExpression expression;

    public GuiComputedQuestion(GuiIdentifierValue identifier, GuiLabelElement labelElement, GuiComputedElement valueElement, GuiExpression expression) {
        super(identifier, labelElement);
        this.valueElement = valueElement;
        this.expression = expression;
    }

    public GuiComputedElement getValueElement() {
        return valueElement;
    }

    public GuiExpression getExpression() {
        return expression;
    }

    @Override
    public GuiComputedElement getElement() {
        return valueElement;
    }

    @Override
    public void accept(GuiBaseElementsVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isQuestion() {
        return true;
    }

    @Override
    public void isVisible(boolean flag) {//FIXME doesnt work properly
        valueElement.getWidget().getParent().setVisible(flag);
        labelElement.getWidget().getParent().setVisible(flag);
        if (!flag) {
            valueElement.clear();
        }
    }
}
