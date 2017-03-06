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
        System.out.println(calculationDependencies);
        return expandAndCheckUsedVariables(calculationDeclaration, calculationDependencies);

    }

    private boolean expandAndCheckUsedVariables(String calculationDeclaration, List<String> usedVariables) {
        for (List<String> expandedVariables = new ArrayList<>();
             usedVariables != expandedVariables;
             usedVariables = expandVariables(expandedVariables)) {
            expandedVariables = usedVariables;
            if (expandedVariables.contains(calculationDeclaration)) {
                return true;
            }
        }
        return false;
    }

    private List<String> expandVariables(List<String> usedVariables) {
        List<String> dependencies = new ArrayList<>();
        for (String variableName : usedVariables) {
            dependencies.addAll(replaceWithDeclarations(variableName));
        }
        return dependencies;
    }

    private List<String> replaceWithDeclarations(String usedVariable) {
        List<String> declarations = new ArrayList<>();
        declarations.add(usedVariable);
        for (CalculatedField calc : getCalculations()) {
            if (calc.getId().equals(usedVariable)) {
                declarations = calc.getUsedVariables();
                break;
            }
        }
        return declarations;
    }
}

