package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.model.environment.SymbolTable;

public class VariableLiteralNode extends LiteralNode {
    private final SymbolTable mReference;

    public VariableLiteralNode(String id, SymbolTable reference) {
        super(id);
        mReference = reference;
    }

    @Override
    public String resolveValue() {
        return mReference.resolve(toString());
    }
}
