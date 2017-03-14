package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.types.Type;

public class VariableLiteralNode extends LiteralNode {
    private final SymbolTable mSymbolTable;

    public VariableLiteralNode(String id, SymbolTable symbolTable) {
        super(id);
        mSymbolTable = symbolTable;
    }

    @Override
    public String resolveValue() {
        return mSymbolTable.resolveValue(super.resolveValue());
    }

    @Override
    public boolean isValid() {
        return super.isBoolean();
    }

    @Override
    public Type getType() {
        return mSymbolTable.resolveType(super.resolveValue());
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        if (!symbolTable.contains(super.resolveValue())) {
            semanticsMessages.addError("Variable name not declared: " + super.resolveValue());
        }
    }
}
