package org.uva.taxfree.model.environment;

import org.uva.taxfree.gui.MessageList;
import org.uva.taxfree.model.node.declarations.CalculationNode;
import org.uva.taxfree.model.node.declarations.DeclarationNode;
import org.uva.taxfree.model.node.expression.ExpressionNode;
import org.uva.taxfree.model.types.Type;

import java.util.*;

public class SymbolTable {
    private final List<DeclarationNode> mDeclarationNodes; // All declaration nodes
    private final List<Declaration> mDeclarations;
    private final List<String> mUsedVariables;
    private final List<ExpressionNode> mExpressions;
    private final List<CalculationNode> mCalculations;

    public SymbolTable() {
        mDeclarationNodes = new ArrayList<>();
        mDeclarations = new ArrayList<>();
        mUsedVariables = new ArrayList<>();
        mExpressions = new ArrayList<>();
        mCalculations = new ArrayList<>();
    }

    public void addDependencies(List<DeclarationNode> nodes) {
        for (DeclarationNode n : nodes) {
            addDeclaration(n);
        }
    }

    public void addExpression(ExpressionNode expression) {
        mExpressions.add(expression);
    }

    public void addDeclaration(DeclarationNode node) {
        mDeclarationNodes.add(node);
        mDeclarations.add(new Declaration(node));
    }

    public void addCalculation(CalculationNode calculation) {
        mCalculations.add(calculation);
    }

    public void addVariable(String variableName) {
        mUsedVariables.add(variableName);
    }

    public void updateValue(String variableId, String updatedValue) {
        for (Declaration decl : mDeclarations) {
            if (decl.equals(variableId)) {
                decl.setValue(updatedValue);
                return;
            }
        }
        throw new RuntimeException("Trying to set unknown variable in SymbolTable");
    }

    public String resolveValue(String variableId) {
        for (Declaration declaration : mDeclarations) {
            if (declaration.equals(variableId)) {
                return declaration.getValue();
            }
        }
        throw new RuntimeException("Unknown variable queried in SymbolTable");
    }

    public boolean contains(String variableId) {
        return findDeclarations(variableId).size() > 0;
    }

    private Declaration findDeclaration(String variableId) {
        assert findDeclarations(variableId).size() == 1;
        return findDeclarations(variableId).get(0);
    }

    private List<Declaration> findDeclarations(String variableId) {
        List<Declaration> declarations = new ArrayList<>();
        for (Declaration decl : mDeclarations) {
            if (decl.equals(variableId)) {
                declarations.add(decl);
            }
        }
        return declarations;
    }

    public Type resolveType(String variableId) {
        return findDeclaration(variableId).getType();
    }

    public void getDuplicateLabelErrors(MessageList messageList) {
        Set<String> processedLabels = new LinkedHashSet<>();
        for (Declaration declaration : mDeclarations) {
            String label = declaration.getLabel();
            if (!processedLabels.add(label)) {
                messageList.addWarning("Duplicate question label found: " + label);
            }
        }
    }

    public void getDuplicateDeclarationErrors(MessageList messageList) {
        Set<String> processedDeclarations = new LinkedHashSet<>();
        for (Declaration declaration : mDeclarations) {
            String id = declaration.getId();
            if (!processedDeclarations.add(id)) {
                messageList.addError("Duplicate declaration found: " + id);
            }
        }
    }

    /*
    public void getUndefinedDeclarationErrros(MessageList messageList) {
        for (String identifier : mUsedVariables) {
            if (!validDeclaration(identifier)) {
                messageList.addError("No declaration found: " + identifier);
            }
        }
    }

    private boolean validDeclaration(String identifier) {
        for (Declaration declaration : mDeclarations) {
            if (declaration.equals(identifier)) {
                return true;
            }
        }
        return false;
    }
*/
    public void generateDependencies(Set<String> usedVariables) {
        Set<String> dependencies = new HashSet<>(usedVariables);
        for (String variableName : usedVariables) {
            addDependencies(variableName, dependencies);
        }

        if (!usedVariables.equals(dependencies)) {
            usedVariables.addAll(dependencies);
            generateDependencies(usedVariables);
        }
    }

    private void addDependencies(String usedVariable, Set<String> usedVariables) {
        for (CalculationNode calc : mCalculations) {
            if (calc.getId().equals(usedVariable)) {
                usedVariables.addAll(calc.getUsedVariables());
            }
        }
    }

}
