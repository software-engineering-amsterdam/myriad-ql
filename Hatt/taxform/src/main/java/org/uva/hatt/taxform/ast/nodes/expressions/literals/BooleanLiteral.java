package org.uva.hatt.taxform.ast.nodes.expressions.literals;

import org.uva.hatt.taxform.ast.nodes.expressions.Expression;
import org.uva.hatt.taxform.ast.nodes.expressions.ExpressionVisitor;

public class BooleanLiteral extends Expression{

    private final boolean value;

    public BooleanLiteral(int lineNumber, boolean value) {
        super(lineNumber);
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> expressionVisitor){
        return expressionVisitor.visit(this);
    }
}
