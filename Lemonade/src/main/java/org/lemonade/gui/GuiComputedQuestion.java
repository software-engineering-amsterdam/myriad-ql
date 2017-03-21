package org.lemonade.gui;

import org.lemonade.gui.elements.GuiComputedElement;
import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.GuiLabelElement;
import org.lemonade.gui.values.GuiIdentifierValue;
import org.lemonade.visitors.EvaluateVisitor;
import org.lemonade.visitors.interfaces.GuiBaseElementsVisitor;

/**
 *
 */
public class GuiComputedQuestion extends GuiBody {

    private GuiIdentifierValue identifier;
    private GuiLabelElement labelElement;
    private GuiComputedElement valueElement;
    private GuiExpression expression;

    public GuiComputedQuestion(GuiIdentifierValue identifier, GuiLabelElement labelElement, GuiComputedElement valueElement, GuiExpression expression) {
        this.identifier = identifier;
        this.labelElement = labelElement;
        this.valueElement = valueElement;
        this.expression = expression;
    }

    public GuiLabelElement getLabelElement() {
        return labelElement;
    }

    public GuiComputedElement getValueElement() {
        return valueElement;
    }

    public GuiExpression getExpression() {
        return expression;
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
    public void isVisible(boolean flag) {
        valueElement.getWidget().getParent().setVisible(flag);
        labelElement.getWidget().getParent().setVisible(flag);
        if (!flag) {
            valueElement.clear();
            labelElement.clear();
        }
    }
}
