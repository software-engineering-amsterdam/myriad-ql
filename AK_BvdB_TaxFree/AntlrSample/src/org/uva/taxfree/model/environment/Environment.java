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

