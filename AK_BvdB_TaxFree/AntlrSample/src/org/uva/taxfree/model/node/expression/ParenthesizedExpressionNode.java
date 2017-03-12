package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.types.Type;

import java.util.Set;

public class ParenthesizedExpressionNode extends ExpressionNode {
    private final ExpressionNode mExpression;

    public ParenthesizedExpressionNode(ExpressionNode expression) {
        super();
        mExpression = expression;
    }

    @Override
    public String resolveValue() {
        return mExpression.resolveValue();
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        symbolTable.addExpression(mExpression);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Type getType() {
        return mExpression.getType();
    }

    @Override
    public void getDependencies(Set<String> dependencies) {
        mExpression.getDependencies(dependencies);
    }
}
