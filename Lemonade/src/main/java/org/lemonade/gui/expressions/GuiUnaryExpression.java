package org.lemonade.gui.expressions;

import org.lemonade.gui.GuiExpression;

/**
 *
 */
public class GuiUnaryExpression implements GuiExpression{

    private GuiExpression expression;

    public GuiUnaryExpression(GuiExpression expression) {
        this.expression = expression;
    }

    public GuiExpression getExpression() {
        return expression;
    }
}
