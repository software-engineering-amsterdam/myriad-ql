package org.uva.taxfree.model.environment;

import org.uva.taxfree.model.node.blocks.BlockNode;
import org.uva.taxfree.model.node.blocks.FormNode;

public class Environment {
    private final SymbolTable mSymbolTable;
    private final BlockNode mAbstractSyntaxTree;

    public Environment(SymbolTable symbolTable, FormNode abstractSyntaxTree) {
        mSymbolTable = symbolTable;
        mAbstractSyntaxTree = abstractSyntaxTree;
    }

    public BlockNode getRootNode() {
        return mAbstractSyntaxTree;
    }


}

