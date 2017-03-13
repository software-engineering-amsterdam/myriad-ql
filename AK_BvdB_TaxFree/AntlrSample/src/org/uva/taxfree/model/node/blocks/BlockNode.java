package org.uva.taxfree.model.node.blocks;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;
import org.uva.taxfree.model.node.Node;

import java.util.Set;

public abstract class BlockNode extends Node {
    private final Set<Node> mChildren;

    public BlockNode(Set<Node> children) {
        mChildren = children; ///< preserves the order in which the items were inserted
    }

    public void setVisible(boolean isVisible) {
        for (Node child : mChildren) {
            child.setVisible(isVisible);
        }
    }

    protected abstract boolean isVisible();

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        for (Node child : mChildren) {
            child.fillSymbolTable(symbolTable);
        }
    }

    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        for (Node child : mChildren) {
            child.checkSemantics(symbolTable, semanticsMessages);
        }
    }
}
