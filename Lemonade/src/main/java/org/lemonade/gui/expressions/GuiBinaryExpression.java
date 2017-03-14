package org.lemonade.gui.expressions;

import org.lemonade.gui.GuiExpression;
import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

/**
 *
 */
public abstract class GuiBinaryExpression implements GuiExpression {

    private GuiExpression left;
    private GuiExpression right;

    public GuiBinaryExpression(GuiExpression left, GuiExpression right) {
        this.left = left;
        this.right = right;
    }

    public GuiExpression getLeft() {
        return left;
    }

    public GuiExpression getRight() {
        return right;
    }

    public abstract <T> T accept(GuiExpressionVisitor<T> visitor);
}
