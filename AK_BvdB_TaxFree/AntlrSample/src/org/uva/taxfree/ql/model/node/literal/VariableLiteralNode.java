package org.uva.taxfree.ql.model.node.literal;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.ql.model.values.Value;

import java.util.Set;

public class VariableLiteralNode extends LiteralNode {
    private SymbolTable mSymbolTable;
    private final String mId;

    public VariableLiteralNode(String id, SourceInfo sourceInfo) {
        super(sourceInfo);
        mId = id;
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        super.fillSymbolTable(symbolTable);
        mSymbolTable = symbolTable;
    }

    @Override
    public Value evaluate() {
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
    public void getDependencies(Set<String> dependencies) {
        dependencies.add(mId);
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        if (!symbolTable.contains(mId)) {
            semanticsMessages.addError("Variable name not declared: " + mId);
        }
    }

    @Override
    public boolean isConstant() {
        return false;
    }
}

