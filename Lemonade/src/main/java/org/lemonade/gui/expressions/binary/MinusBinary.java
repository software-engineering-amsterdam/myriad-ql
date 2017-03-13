package org.lemonade.gui.expressions.binary;

import org.lemonade.gui.GuiExpression;
import org.lemonade.gui.expressions.GuiBinaryExpression;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

/**
 *
 */
public class MinusBinary extends GuiBinaryExpression {

    public MinusBinary(GuiExpression left, GuiExpression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(GuiExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
