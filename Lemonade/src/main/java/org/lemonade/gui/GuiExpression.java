package org.lemonade.gui;

import org.lemonade.visitors.interfaces.GuiExpressionVisitor;

public interface GuiExpression {

    <T> T accept(GuiExpressionVisitor<T> visitor);

}
