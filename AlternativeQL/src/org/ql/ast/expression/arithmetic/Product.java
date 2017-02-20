package org.ql.ast.expression.arithmetic;

import org.ql.ast.Expression;
import org.ql.ast.expression.Visitor;

public class Product implements Expression {
    private final Expression left;
    private final Expression right;

    public Product(Expression left, Expression right) {
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
        return "(" + left + "*" + right + ")";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
