package org.lemonade.visitors;

import sun.tools.tree.BinaryExpression;
import sun.tools.tree.UnaryExpression;

/**
 *
 */
public interface ExpressionVisitor<T> {
    T visitBinaryExpression(BinaryExpression expression);
    T visitUnaryExpression(UnaryExpression expression);
}
