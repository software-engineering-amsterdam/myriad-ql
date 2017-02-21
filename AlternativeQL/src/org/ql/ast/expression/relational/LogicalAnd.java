package org.ql.ast.expression.relational;

import org.ql.ast.Expression;
import org.ql.ast.expression.BinaryExpression;
import org.ql.ast.expression.Visitor;

public class LogicalAnd extends BinaryExpression {

    public LogicalAnd(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + getLeft() + "&&" + getRight() + ")";
    }

    @Override
    public <T> T accept(Visitor<T> visitor) throws Throwable {
        return visitor.visit(this);
    }
}
