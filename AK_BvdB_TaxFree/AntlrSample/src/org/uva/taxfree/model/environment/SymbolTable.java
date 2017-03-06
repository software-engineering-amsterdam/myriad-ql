package org.uva.taxfree.model.environment;

import org.uva.taxfree.model.node.declarations.NamedNode;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SymbolTable {
    private final Set<NamedNode> mDeclarations; // All declarations
    private final Set<String> mUsedVariables;

    public SymbolTable() {
        mDeclarations = new LinkedHashSet<>();
        mUsedVariables = new LinkedHashSet<>();
    }

    public void addDeclarations(Set<NamedNode> nodes) {
        for (NamedNode n : nodes) {
            addDeclaration(n);
        }
    }

    public void addDeclaration(NamedNode Node) {
        mDeclarations.add(Node);
    }

    public void addVariable(String variableName) {
        mUsedVariables.add(variableName);
    }

    public String resolve(String variableId) {
        for (NamedNode n : mDeclarations) {
            if (variableId.equals(n.getId())) {
                return (n.resolveValue());
            }
        }
        throw new RuntimeException("Unable to resolveValue id: " + variableId);
    }

    public List<String> getDuplicateLabels() {
        List<String> duplicateLabels = new ArrayList<>();
        Set<String> processedLabels = new LinkedHashSet<>();
        for (NamedNode node : mDeclarations) {
            String questionLabel = node.getLabel();
            if (!processedLabels.add(questionLabel)) {
                duplicateLabels.add("Duplicate question label found: " + questionLabel);
            }
        }
        return duplicateLabels;
    }


}
