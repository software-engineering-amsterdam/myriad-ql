package org.uva.taxfree.model.environment;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.node.declarations.CalculatedField;
import org.uva.taxfree.model.node.declarations.NamedNode;
import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.types.Type;

import java.util.*;

public class SymbolTable {
    private final List<NamedNode> mDeclarations; // All declarations
    private final List<String> mUsedVariables;
    private final List<ExpressionNode> mExpressions;
    private final List<CalculatedField> mCalculations;


    public SymbolTable() {
        mDeclarations = new ArrayList<>();
        mUsedVariables = new ArrayList<>();
        mExpressions = new ArrayList<>();
        mCalculations = new ArrayList<>();
    }

    public void addDepencendies(Set<NamedNode> nodes) {
        for (NamedNode n : nodes) {
            addDeclaration(n);
        }
    }

    public void addExpression(ExpressionNode expression) {
        mExpressions.add(expression);
    }

    public void addDeclaration(NamedNode node) {
        mDeclarations.add(node);
    }

    public void addCalculation(CalculatedField calculation) {
        mCalculations.add(calculation);
    }

    public void addVariable(String variableName) {
        mUsedVariables.add(variableName);
    }

    public String resolveValue(String variableId) {
        return findNode(variableId).resolveValue();
    }

    public Type resolveType(String variableId) {
        return findNode(variableId).getType();
    }

    private NamedNode findNode(String variableId) {
        for (NamedNode n : mDeclarations) {
            if (variableId.equals(n.getId())) {
                return n;
            }
        }
        throw new RuntimeException("Unresolvable identifier queried");
    }

    public void getDuplicateLabelErrors(MessageList messageList) {
        Set<String> processedLabels = new LinkedHashSet<>();
        for (NamedNode node : mDeclarations) {
            String questionLabel = node.getLabel();
            if (!processedLabels.add(questionLabel)) {
                messageList.addWarning("Duplicate question label found: " + questionLabel);
            }
        }
    }

    public void getDuplicateDeclarationErrors(MessageList messageList) {
        Set<String> processedDeclarations = new LinkedHashSet<>();
        for (NamedNode node : mDeclarations) {
            String declaration = node.getId();
            if (!processedDeclarations.add(declaration)) {
                messageList.addError("Duplicate declaration found: " + declaration);
            }
        }
    }


    public void getUndefinedDeclarationErrros(MessageList messageList) {
        for (String identifier : mUsedVariables) {
            if (!validDeclaration(identifier)) {
                messageList.addError("No declaration found: " + identifier);
            }
        }
    }

    private boolean validDeclaration(String identifier) {
        for (NamedNode node : mDeclarations) {
            if (identifier.equals(node.getId())) {
                return true;
            }
        }
        return false;
    }


    public void generateDependencies(Set<String> usedVariables) {
        Set<String> dependencies = new HashSet<>(usedVariables);
        for (String variableName : usedVariables) {
            addDepencendies(variableName, dependencies);
        }

        if (!usedVariables.equals(dependencies)) {
            usedVariables.addAll(dependencies);
            generateDependencies(usedVariables);
        }
    }

    private void addDepencendies(String usedVariable, Set<String> usedVariables) {
        for (CalculatedField calc : mCalculations) {
            if (calc.getId().equals(usedVariable)) {
                usedVariables.addAll(calc.getUsedVariables());
            }
        }
    }
}
