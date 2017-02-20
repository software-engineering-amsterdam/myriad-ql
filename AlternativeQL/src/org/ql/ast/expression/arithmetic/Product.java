package org.ql.ast.expression.arithmetic;

import org.ql.ast.Expression;
import org.ql.ast.Metadata;
import org.ql.ast.Visitor;

public class Product extends Expression {
    private final Expression left;
    private final Expression right;

    public Product(Expression left, Expression right, Metadata metadata) {
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
        return "(" + left + "*" + right + ")";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
