package org.lemonade.gui.expressions.unary;

import org.lemonade.gui.GuiExpression;
import org.lemonade.gui.expressions.GuiUnaryExpression;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public class GuiNegUnary extends GuiUnaryExpression {

    public GuiNegUnary(GuiExpression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
