package com.matthewchapman.ql.ast.expression.binary;

import com.matthewchapman.ql.ast.Expression;
import com.matthewchapman.ql.validation.visitors.QLExpressionVisitor;

/**
 * Created by matt on 24/02/2017.
 * <p>
 * Logical AND class
 */
public class LogicalAnd extends BinaryOperation {

    public LogicalAnd(Expression left, Expression right, int line, int charPositionInLine) {
        super(left, right);
        super.setLine(line);
        super.setColumn(charPositionInLine);
    }

    @Override
    public String toString() {
        return "(" + getLeft() + " && " + getRight() + ")";
    }

    @Override
    public <T, C> T accept(QLExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
