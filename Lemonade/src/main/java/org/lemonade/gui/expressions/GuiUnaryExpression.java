package org.lemonade.gui.expressions;

import org.lemonade.gui.GuiExpression;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

/**
 *
 */
public abstract class GuiUnaryExpression implements GuiExpression{

    private GuiExpression expression;

    public GuiUnaryExpression(GuiExpression expression) {
        this.expression = expression;
    }

    public GuiExpression getExpression() {
        return expression;
    }

    public abstract <T> T accept(GuiExpressionVisitor<T> visitor);
}
