package org.uva.taxfree.model.environment;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.node.blocks.BlockNode;
import org.uva.taxfree.model.node.blocks.FormNode;
import org.uva.taxfree.model.node.declarations.CalculatedField;
import org.uva.taxfree.model.node.expression.ExpressionNode;

import java.util.LinkedHashSet;
import java.util.Set;

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

    public void getDuplicateLabelErrors(MessageList messageList) {
        mSymbolTable.getDuplicateLabelErrors(messageList);
    }

    public void getDuplicateDeclarationErrors(MessageList messageList) {
        mSymbolTable.getDuplicateDeclarationErrors(messageList);
    }

    public void getUndefinedDeclarationErrors(MessageList messageList) {
        mSymbolTable.getUndefinedDeclarationErrros(messageList);
    }

    public void getConditionErrors(MessageList messageList) {
        Set<ExpressionNode> conditions = new LinkedHashSet<>();
        mAbstractSyntaxTree.retrieveConditions(conditions);
        for (ExpressionNode expressionNode : conditions) {
            if (!expressionNode.isValid()) {
                messageList.addError("Condition found with invalid types: " + expressionNode.resolveValue());
            }
        }
    }

    // checkcyclicdependencies
    public void getCyclicDependencyErrors(MessageList messageList) {
        for (CalculatedField calculation : getCalculations()) {
            if (hasCyclicDependency(calculation)) {
                messageList.addError("Cyclic dependency found: " + calculation.getId());
            }
        }
    }

    private boolean hasCyclicDependency(CalculatedField calc) {
        Set<String> dependencies = calc.getUsedVariables();
        substituteVariables(dependencies);
        return dependencies.contains(calc.getId());
    }

    private void substituteVariables(Set<String> usedVariables) {

        Set<String> dependencies = new LinkedHashSet<>(usedVariables);
        for (String variableName : usedVariables) {
            addDeclarations(variableName, dependencies);
        }

        if (!usedVariables.equals(dependencies)) {
            usedVariables.addAll(dependencies);
            substituteVariables(usedVariables);
        }
    }


    private void addDeclarations(String usedVariable, Set<String> usedVariables) {
        for (CalculatedField calc : getCalculations()) {
            if (calc.getId().equals(usedVariable)) {
                usedVariables.addAll(calc.getUsedVariables());
            }
        }
    }

    private Set<CalculatedField> getCalculations() {
        Set<CalculatedField> calculations = new LinkedHashSet<>();
        mAbstractSyntaxTree.retrieveCalculations(calculations);
        return calculations;
    }
}

