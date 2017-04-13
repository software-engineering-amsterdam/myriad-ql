package org.uva.hatt.taxform.ast.nodes.expressions;

public abstract class BooleanExpression extends Expression{

    private final Expression lhs;
    private final Expression rhs;

    public BooleanExpression(int lineNumber, Expression lhs, Expression rhs) {
        super(lineNumber);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Expression getLhs() {
        return lhs;
    }

    public Expression getRhs() {
        return rhs;
    }
}
