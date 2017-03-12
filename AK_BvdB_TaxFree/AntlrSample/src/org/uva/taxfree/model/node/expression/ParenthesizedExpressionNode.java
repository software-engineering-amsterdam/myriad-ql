package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.types.Type;

public class ParenthesizedExpressionNode extends ExpressionNode {
    private final ExpressionNode mCondition;

    public ParenthesizedExpressionNode(ExpressionNode condition) {
        super();
        mCondition = condition;
    }

    @Override
    public String resolveValue() {
        return mCondition.resolveValue();
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        symbolTable.addExpression(mCondition);
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public Type getType() {
        return mCondition.getType();
    }
}
