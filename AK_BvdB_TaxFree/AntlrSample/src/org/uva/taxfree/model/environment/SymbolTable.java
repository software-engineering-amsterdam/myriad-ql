package org.uva.taxfree.model.environment;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.node.declarations.CalculationNode;
import org.uva.taxfree.model.node.declarations.DeclarationNode;
import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.types.Type;

import java.util.*;

public class SymbolTable {
    private final List<DeclarationNode> mDeclarations; // All declarations
    private final List<String> mUsedVariables;
    private final List<ExpressionNode> mExpressions;
    private final List<CalculationNode> mCalculations;


    public SymbolTable() {
        mDeclarations = new ArrayList<>();
        mUsedVariables = new ArrayList<>();
        mExpressions = new ArrayList<>();
        mCalculations = new ArrayList<>();
    }

    public void addDepencendies(List<DeclarationNode> nodes) {
        for (DeclarationNode n : nodes) {
            addDeclaration(n);
        }
    }

    public void addExpression(ExpressionNode expression) {
        mExpressions.add(expression);
    }

    public void addDeclaration(DeclarationNode node) {
        mDeclarations.add(node);
    }

    public void addCalculation(CalculationNode calculation) {
        mCalculations.add(calculation);
    }

    public void addVariable(String variableName) {
        mUsedVariables.add(variableName);
    }

    public String resolveValue(String variableId) {
        throw new RuntimeException("Not implemented yet");
        //return findNode(variableId).resolveValue();
    }

    public boolean contains(String variableId) {
        return findNodes(variableId).size() > 0;
    }

    private DeclarationNode findNode(String variableId) {
        assert findNodes(variableId).size() == 1;
        return findNodes(variableId).get(0);
    }

    private List<DeclarationNode> findNodes(String variableId) {
        List<DeclarationNode> declarationNodes = new ArrayList<>();
        for (DeclarationNode n : mDeclarations) {
            if (variableId.equals(n.getId())) {
                declarationNodes.add(n);
            }
        }
        return declarationNodes;
    }

    public Type resolveType(String variableId) {
        return findNode(variableId).getType();
    }

    public void getDuplicateLabelErrors(MessageList messageList) {
        Set<String> processedLabels = new LinkedHashSet<>();
        for (DeclarationNode node : mDeclarations) {
            String questionLabel = node.getLabel();
            if (!processedLabels.add(questionLabel)) {
                messageList.addWarning("Duplicate question label found: " + questionLabel);
            }
        }
    }

    public void getDuplicateDeclarationErrors(MessageList messageList) {
        Set<String> processedDeclarations = new LinkedHashSet<>();
        for (DeclarationNode node : mDeclarations) {
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
        for (DeclarationNode node : mDeclarations) {
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
        for (CalculationNode calc : mCalculations) {
            if (calc.getId().equals(usedVariable)) {
                usedVariables.addAll(calc.getUsedVariables());
            }
        }
    }
}
