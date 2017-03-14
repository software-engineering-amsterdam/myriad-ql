package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.types.Type;

import java.util.Set;

public class VariableLiteralNode extends LiteralNode {
    private SymbolTable mSymbolTable;

    public VariableLiteralNode(String id) {
        super(id);
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        super.fillSymbolTable(symbolTable);
        mSymbolTable = symbolTable;
    }

    public String resolveValue() {
        assert symbolTableValid();
        return mSymbolTable.resolveValue(super.resolveValue());
    }

    public Type getType() {
        assert symbolTableValid();
        return mSymbolTable.resolveType(super.resolveValue());
    }

    private boolean symbolTableValid() {
        return mSymbolTable != null;
    }

    @Override
    public void getDependencies(Set<String> dependencies) {
        dependencies.add(super.resolveValue());
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        if (!symbolTable.contains(super.resolveValue())) {
            semanticsMessages.addError("Variable name not declared: " + super.resolveValue());
        }
    }
}

