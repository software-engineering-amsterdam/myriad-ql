package org.ql.ast.expression.relational;

import org.ql.ast.Expression;
import org.ql.ast.Metadata;
import org.ql.ast.Visitor;

public class LogicalOr extends Expression {
    private Expression left;
    private Expression right;

    public LogicalOr(Expression left, Expression right, Metadata metadata) {
        this.left = left;
        this.right = right;
        this.metadata = metadata;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "(" + left + "||" + right + ")";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
