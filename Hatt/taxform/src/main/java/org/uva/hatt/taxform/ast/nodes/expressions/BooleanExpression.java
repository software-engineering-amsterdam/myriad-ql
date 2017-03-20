package org.uva.hatt.taxform.ast.nodes.expressions;

import org.uva.hatt.taxform.ast.visitors.Visitor;

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

    @Override
    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
