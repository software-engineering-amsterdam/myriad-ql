package org.ql.ast.expression.relational;

import org.ql.ast.Expression;
import org.ql.ast.Visitor;

public class NotEqual implements Expression {
    private Expression left;
    private Expression right;

    public NotEqual(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "(" + left + "!=" + right + ")";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
