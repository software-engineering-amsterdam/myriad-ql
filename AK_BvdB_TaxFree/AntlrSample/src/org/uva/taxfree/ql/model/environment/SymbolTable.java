package org.uva.taxfree.ql.model.environment;

import org.uva.taxfree.ql.gui.MessageList;
import org.uva.taxfree.ql.model.node.blocks.BlockNode;
import org.uva.taxfree.ql.model.node.declarations.CalculationNode;
import org.uva.taxfree.ql.model.node.declarations.DeclarationNode;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.ql.model.types.UnknownType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SymbolTable {
    private final List<Declaration> mDeclarations;
    private final List<CalculationNode> mCalculations;
    private final List<BlockNode> mBlocks;

    public SymbolTable() {
        mDeclarations = new ArrayList<>();
        mBlocks = new ArrayList<>();
        mCalculations = new ArrayList<>();
    }

    public void addBlock(BlockNode blockNode) {
        mBlocks.add(blockNode);
    }

    public void addDeclaration(DeclarationNode node) {
        mDeclarations.add(new Declaration(node));
    }

    public void addCalculation(CalculationNode calculation) {
        mCalculations.add(calculation);
    }

    public void updateValue(String variableId, String updatedValue) {
        updateDeclaration(variableId, updatedValue);
        recalculate();
    }

    private void updateDeclaration(String variableId, String updatedValue) {
        for (Declaration declaration : mDeclarations) {
            if (declaration.equals(variableId)) {
                declaration.setValue(updatedValue);
            }
        }
    }

    private void recalculate() {
        for (CalculationNode calculation : mCalculations) {
            updateDeclaration(calculation.getId(), calculation.resolveValue());
        }
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

    public Type resolveType(String variableId) {
        if (true == contains(variableId)) {
            return findDeclaration(variableId).getType();
        }
        return new UnknownType();
    }

    private Declaration findDeclaration(String variableId) {
        assert findDeclarations(variableId).size() == 1;
        return findDeclarations(variableId).get(0);
    }

    private List<Declaration> findDeclarations(String variableId) {
        List<Declaration> declarations = new ArrayList<>();
        for (Declaration declaration : mDeclarations) {
            if (declaration.equals(variableId)) {
                declarations.add(declaration);
            }
        }
        return declarations;
    }

    public void checkDuplicateLabels(MessageList messageList) {
        Set<String> processedLabels = new LinkedHashSet<>();
        for (Declaration declaration : mDeclarations) {
            String label = declaration.getLabel();
            if (!processedLabels.add(label)) {
                messageList.addWarning("Duplicate question label found: " + label);
            }
        }
    }

    public void checkDuplicateDeclarations(MessageList messageList) {
        Set<String> processedDeclarations = new LinkedHashSet<>();
        for (Declaration declaration : mDeclarations) {
            String id = declaration.getId();
            if (!processedDeclarations.add(id)) {
                messageList.addError("Duplicate declaration found: " + id);
            }
        }
    }

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
        for (CalculationNode calculation : mCalculations) {
            if (calculation.getId().equals(usedVariable)) {
                usedVariables.addAll(calculation.getUsedVariables());
            }
        }
    }

    public boolean isVisible(String variableId) {
        return visibleIds().contains(variableId);
    }

    private List<String> visibleIds() {
        List<String> visibleDeclarations = new ArrayList<>();
        for (BlockNode blockNode : mBlocks) {
            blockNode.generateVisibleIds(visibleDeclarations);
        }
        return visibleDeclarations;
    }

    public void export(String fileName) throws IOException {
        FileWriter results = new FileWriter(fileName);
        for (Declaration declaration : mDeclarations) {
            results.write(declaration.getId() + ":" + declaration.getValue() + "\r\n");
        }
        results.close();
    }
}
