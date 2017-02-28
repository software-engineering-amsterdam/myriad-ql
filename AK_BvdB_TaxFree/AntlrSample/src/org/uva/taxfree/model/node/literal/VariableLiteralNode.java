package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.model.environment.SymbolTable;

public class VariableLiteralNode extends LiteralNode {
    private final SymbolTable mSymbolTable;

    public VariableLiteralNode(String id, SymbolTable symbolTable) {
        super(id);
        mSymbolTable = symbolTable;
    }

    @Override
    public String resolveValue() {
        return mSymbolTable.resolve(super.resolveValue());
    }
}
