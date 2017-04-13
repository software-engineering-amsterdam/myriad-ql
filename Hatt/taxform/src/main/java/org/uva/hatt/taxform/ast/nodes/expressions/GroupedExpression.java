package org.uva.hatt.taxform.ast.nodes.expressions;

import org.uva.hatt.taxform.ast.visitors.ExpressionVisitor;

public class GroupedExpression extends Expression{

    private final Expression expression;

    public GroupedExpression(int lineNumber, Expression expression) {
        super(lineNumber);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> expressionVisitor){
        return expressionVisitor.visit(this);
    }
}
