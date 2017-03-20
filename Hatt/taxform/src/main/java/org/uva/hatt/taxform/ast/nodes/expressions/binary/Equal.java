package org.uva.hatt.taxform.ast.nodes.expressions.binary;

import org.uva.hatt.taxform.ast.nodes.expressions.BooleanExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.visitors.Visitor;

public class Equal extends BooleanExpression {

    public Equal(int lineNumber, Expression lhs, Expression rhs){
        super(lineNumber, lhs, rhs);
    }

    @Override
    public <T> T accept(Visitor<T> visitor){
        return visitor.visit(this);
    }
}
