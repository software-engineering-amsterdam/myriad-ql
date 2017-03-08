package org.uva.taxfree.model.node;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.environment.SymbolTable;

public abstract class Node {
    public void setVisible(boolean isVisible) {
        // Intentionally left blank
    }

    public abstract void fillSymbolTable(SymbolTable symbolTable);

    public /*abstract*/ void checkSemantics(SymbolTable symbolTable, MessageList messageList) {
        // TODO: make abstract and do the checks!
        messageList.addError("checkSemantics unimplemented!");
    }

}