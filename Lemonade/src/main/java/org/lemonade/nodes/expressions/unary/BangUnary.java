package org.lemonade.nodes.expressions.unary;

import org.lemonade.exeptions.QLExpressionException;
import org.lemonade.exeptions.QLOperatorException;
import org.lemonade.nodes.Position;
import org.lemonade.nodes.expressions.Expression;
import org.lemonade.nodes.expressions.UnaryExpression;
import org.lemonade.visitors.ASTVisitor;

/**
 *
 */
public class BangUnary extends UnaryExpression {

    public BangUnary(Expression expr) {
        super(expr);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
