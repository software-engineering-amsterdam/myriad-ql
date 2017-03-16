package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.types.Type;

import java.util.Set;

public class VariableLiteralNode extends LiteralNode {
    private SymbolTable mSymbolTable;
    private final String mId;

    public VariableLiteralNode(String id) {
        mId = id;
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        super.fillSymbolTable(symbolTable);
        mSymbolTable = symbolTable;
    }

    public String resolveValue() {
        assert symbolTableValid();
        return mSymbolTable.resolveValue(mId);
    }

    public Type getType() {
        assert symbolTableValid();
        return mSymbolTable.resolveType(mId);
    }

    private boolean symbolTableValid() {
        return mSymbolTable != null;
    }

    @Override
    protected boolean asBoolean() {
        return Boolean.valueOf(resolveValue());
    }

    @Override
    protected int asInteger() {
        return Integer.valueOf(resolveValue());
    }

    @Override
    protected String asString() {
        return resolveValue();
    }

    @Override
    public void getDependencies(Set<String> dependencies) {
        dependencies.add(mId);
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        if (!symbolTable.contains(mId)) {
            semanticsMessages.addError("Variable name not declared: " + mId);
        }
    }
}

