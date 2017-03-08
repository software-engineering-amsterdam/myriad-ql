package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.expression.ExpressionNode;

public abstract class LiteralNode extends ExpressionNode {
    private final String mId;

    public LiteralNode(String id) {
        super();
        mId = id;
    }

    public String resolveValue() {
        return mId;
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        symbolTable.addVariable(mId);
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
