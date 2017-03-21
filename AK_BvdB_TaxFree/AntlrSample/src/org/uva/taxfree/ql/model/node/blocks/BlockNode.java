package org.uva.taxfree.ql.model.node.blocks;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.model.SourceInfo;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.node.Node;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BlockNode extends Node {
    private final List<Node> mChildren;

    public BlockNode(List<Node> children, SourceInfo sourceInfo) {
        super(sourceInfo);
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

    protected void checkCyclicDependency(Set<String> dependencies, SymbolTable symbolTable, MessageList semanticsMessages) {
        Set<String> childVariables = new HashSet<>();
        for (Node child : mChildren) {
            child.collectUsedVariables(childVariables);
        }
        symbolTable.generateDependencies(dependencies);
        for (String variableName : dependencies) {
            if (childVariables.contains(variableName)) {
                semanticsMessages.addError("Condition depends on inner declaration: " + variableName);
            }
        }
    }

    @Override
    public void collectUsedVariables(Set<String> dependencies) {
        for (Node child : mChildren) {
            child.collectUsedVariables(dependencies);
        }
    }

    protected abstract boolean conditionTrue();
}
