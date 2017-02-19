package org.lemonade.visitors;

import org.lemonade.nodes.expressions.Expression;

/**
 *
 */
public interface ExpressionVisitor<T> {
    T visit(Expression expression);
}
