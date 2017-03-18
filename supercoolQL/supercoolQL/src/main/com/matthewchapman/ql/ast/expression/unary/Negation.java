package com.matthewchapman.ql.ast.expression.unary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.visitor.QLExpressionVisitor;

/**
 * Created by matt on 24/02/2017.
 * <p>
 * Binary negation class
 */
public class Negation extends UnaryOperation {

    private final Expression expression;

    public Negation(Expression expression, int line, int column) {
        this.expression = expression;
        super.setLine(line);
        super.setColumn(column);
    }

    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public String toString() {
        return "!(" + getExpression() + ")";
    }

    @Override
    public <T, C> T accept(QLExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
