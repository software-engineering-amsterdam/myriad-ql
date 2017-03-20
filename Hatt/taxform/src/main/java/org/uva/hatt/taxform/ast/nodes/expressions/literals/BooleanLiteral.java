package org.uva.hatt.taxform.ast.nodes.expressions.literals;

import org.uva.hatt.taxform.ast.visitors.Visitor;

public class BooleanLiteral extends Literal{

    public BooleanLiteral(int lineNumber, String id) {
        super(lineNumber, id);
    }

    @Override
    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
