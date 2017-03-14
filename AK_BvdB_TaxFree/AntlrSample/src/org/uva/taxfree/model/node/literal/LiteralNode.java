package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.expression.ExpressionNode;

import java.util.Set;

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

    @Override
    public void getDependencies(Set<String> dependencies) {
        dependencies.add(mId);
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        // intentionally left blank
    }
}
