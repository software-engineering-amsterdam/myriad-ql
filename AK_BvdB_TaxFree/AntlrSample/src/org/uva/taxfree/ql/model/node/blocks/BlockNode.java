package org.uva.taxfree.ql.model.node.blocks;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.Node;

import java.util.List;

public abstract class BlockNode extends Node {
    private final List<Node> mChildren;

    public BlockNode(List<Node> children) {
        mChildren = children; ///< preserves the order in which the items were inserted
    }

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

    @Override
    public void fillQuestionForm(QuestionForm form) {
        for (Node child : mChildren) {
            child.fillQuestionForm(form);
        }
    }

    public void generateVisibleIds(List<String> visibleIds) {
        if (conditionTrue()) {
            for (Node child : mChildren) {
                child.generateVisibleIds(visibleIds);
            }
        }
    }

    protected abstract boolean conditionTrue();
}
