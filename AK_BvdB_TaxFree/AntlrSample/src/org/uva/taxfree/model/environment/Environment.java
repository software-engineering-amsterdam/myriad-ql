package org.uva.taxfree.model.environment;

import org.uva.taxfree.model.node.blocks.BlockNode;
import org.uva.taxfree.model.node.blocks.FormNode;
import org.uva.taxfree.model.node.declarations.CalculatedField;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Environment {
    private final SymbolTable mSymbolTable;
    private final BlockNode mAbstractSyntaxTree;

    public Environment(SymbolTable symbolTable, FormNode abstractSyntaxTree) {
        mSymbolTable = symbolTable;
        mAbstractSyntaxTree = abstractSyntaxTree;
    }

    public List<String> getDuplicateLabelErrors() {
        return mSymbolTable.getDuplicateLabelErrors();
    }

    public List<String> getDuplicateDeclarationErrors() {
        return mSymbolTable.getDuplicateDeclarationErrors();
    }

    public List<String> getUndefinedDeclarationErrors() {
        return mSymbolTable.getUndefinedDeclarationErrros();
    }

    public List<String>
    getCyclicDependencyErrors() {
        List<String> errors = new ArrayList<>();
        for (CalculatedField calculation : getCalculations()) {
            if (hasCyclicDependency(calculation)) {
                errors.add("Cyclic dependency found: " + calculation.getId());
            }
        }
        return errors;
    }

    private Set<CalculatedField> getCalculations() {
        Set<CalculatedField> calculations = new LinkedHashSet<>();
        mAbstractSyntaxTree.retrieveCalculations(calculations);
        return calculations;
    }

    private boolean hasCyclicDependency(CalculatedField calculation) {
        String calculationDeclaration = calculation.getId();
        List<String> calculationDependencies = calculation.getUsedVariables();
        return expandAndCheckUsedVariables(calculationDeclaration, calculationDependencies);

    }

    private boolean expandAndCheckUsedVariables(String calculationDeclaration, List<String> usedVariables) {
        while (expandVariables(usedVariables)) {
            if (usedVariables.contains(calculationDeclaration)) {
                return true;
            }
        }
        return false;
    }

    private boolean expandVariables(List<String> usedVariables) {
        List<String> dependencies = new ArrayList<>();
        for (String variableName : usedVariables) {
            dependencies.addAll(replaceWithDeclarations(variableName));
        }
        boolean isChanged = dependencies != usedVariables;
        usedVariables.clear();
        usedVariables.addAll(dependencies);
        return isChanged;
    }

    private List<String> replaceWithDeclarations(String usedVariable) {
        for (CalculatedField calc : getCalculations()) {
            if (calc.getId().equals(usedVariable)) {
                return calc.getUsedVariables();
            }
        }
        return new ArrayList<>();
    }
}

