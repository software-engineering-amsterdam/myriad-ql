package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.types.Type;

import java.util.List;
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
        // Intentionally left blank
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public void getDependencies(Set<String> dependencies) {
        // Intentionally left blank
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        // intentionally left blank
    }

    @Override
    public void generateVisibleIds(List<String> visibleIds) {
        // intentionally left blank
    }
}
