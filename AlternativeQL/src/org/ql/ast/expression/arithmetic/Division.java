package org.ql.ast.expression.arithmetic;

import org.ql.ast.Expression;
import org.ql.ast.Visitor;

public class Division implements Expression {
    private final Expression left;
    private final Expression right;

    public Division(Expression left, Expression right) {
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
        return "(" + left + "/" + right + ")";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
