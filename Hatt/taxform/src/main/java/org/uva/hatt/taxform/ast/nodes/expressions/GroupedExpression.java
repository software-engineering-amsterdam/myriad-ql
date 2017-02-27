package org.uva.hatt.taxform.ast.nodes.expressions;

import org.uva.hatt.taxform.ast.visitors.Visitor;

public class GroupedExpression extends Expression{

    public GroupedExpression(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
