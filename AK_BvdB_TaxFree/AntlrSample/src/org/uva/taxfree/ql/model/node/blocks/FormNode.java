package org.uva.taxfree.ql.model.node.blocks;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.Node;

import java.util.List;

public class FormNode extends BlockNode {
    private final String mFormName;

    public FormNode(String label, List<Node> children, SourceInfo sourceInfo) {
        super(children, sourceInfo);
        mFormName = label;
    }

    @Override
    public String toString() {
        return mFormName;
    }


    @Override
    public void checkSemantics(SymbolTable symbolTable, MessageList semanticsMessages) {
        symbolTable.checkDuplicateDeclarations(semanticsMessages);
        symbolTable.checkDuplicateLabels(semanticsMessages);
        super.checkSemantics(symbolTable, semanticsMessages);
    }

    @Override
    public boolean conditionTrue() {
        return true;
    }

    @Override
    public void fillSymbolTable(SymbolTable symbolTable) {
        super.fillSymbolTable(symbolTable);
        symbolTable.addBlock(this);
    }


}
