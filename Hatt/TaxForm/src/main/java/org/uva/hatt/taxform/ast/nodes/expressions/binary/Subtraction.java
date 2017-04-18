package org.uva.hatt.taxform.ast.nodes.expressions.binary;

import org.uva.hatt.taxform.ast.nodes.expressions.BinaryExpression;
import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.ExpressionVisitor;

public class Subtraction extends BinaryExpression {

    public  Subtraction(int lineNumber, Expression lhs, Expression rhs){
        super(lineNumber, lhs, rhs);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> expressionVisitor){
        return expressionVisitor.visit(this);
    }
}
