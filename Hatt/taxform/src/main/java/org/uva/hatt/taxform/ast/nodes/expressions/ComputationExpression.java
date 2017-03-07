package org.uva.hatt.taxform.ast.nodes.expressions;

import org.uva.hatt.taxform.ast.visitors.Visitor;

public class ComputationExpression extends Expression{

    public ComputationExpression(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
