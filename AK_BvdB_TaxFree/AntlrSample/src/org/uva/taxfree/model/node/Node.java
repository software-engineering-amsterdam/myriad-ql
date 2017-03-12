package org.uva.taxfree.model.node;

import org.uva.taxfree.model.environment.SymbolTable;

public abstract class Node {
    public void setVisible(boolean isVisible) {
        // Intentionally left blank
    }

    public abstract void fillSymbolTable(SymbolTable symbolTable);
}