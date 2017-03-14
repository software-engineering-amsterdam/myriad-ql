package org.uva.taxfree.model.node.literal;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.types.Type;

import java.util.Set;

public class VariableLiteralNode extends LiteralNode {
    public VariableLiteralNode(String id) {
        super(id);
    }

    @Override
    public String resolveValue() {
        System.out.println("resolveValue not implemented in VariableLiteralNode");
        throw new RuntimeException("VariableLiteralNode @ resolveValue");
    }

    public String resolveValue(SymbolTable symbolTable) {
        return symbolTable.resolveValue(super.resolveValue());
    }

    @Override
    public boolean isValid() {
        return super.isBoolean();
    }

    public Type getType() {
        System.out.println("getType not implemented in VariableLiteralNode");
        throw new RuntimeException("variableLiteralNode @ getType");
    }

    public Type getType(SymbolTable symbolTable) {
        return symbolTable.resolveType(super.resolveValue());
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

