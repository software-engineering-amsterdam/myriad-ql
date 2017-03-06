package org.uva.taxfree.model.environment;

import org.uva.taxfree.model.node.blocks.BlockNode;
import org.uva.taxfree.model.node.blocks.FormNode;

import java.util.List;

public class Environment {
    private final SymbolTable mSymbolTable;
    private final BlockNode mAbstractSyntaxTree;

    public Environment(SymbolTable symbolTable, FormNode abstractSyntaxTree) {
        mSymbolTable = symbolTable;
        mAbstractSyntaxTree = abstractSyntaxTree;
    }

    public List<String> getDuplicateLabelErrors() {
        return mSymbolTable.getDuplicateLabelErrors();
    }

    public List<String> getDuplicateDeclarationErrors() {
        return mSymbolTable.getDuplicateDeclarationErrors();
    }

    public List<String> getUndefinedDeclarationErrors() {
        return mSymbolTable.getUndefinedDeclarationErrros();
    }
}
