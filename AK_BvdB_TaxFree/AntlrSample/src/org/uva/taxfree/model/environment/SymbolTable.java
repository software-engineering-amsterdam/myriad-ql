package org.uva.taxfree.model.environment;

import org.uva.taxfree.model.node.statement.NamedNode;

import java.util.LinkedHashSet;
import java.util.Set;

public class SymbolTable {
    private final Set<NamedNode> mSymbols; // All declarations

    public SymbolTable() {
        mSymbols = new LinkedHashSet<>();
    }

    public void addSymbol(NamedNode Node) {
        mSymbols.add(Node);
    }

    public String resolve(String variableId) {
        for (NamedNode n : mSymbols) {
            if (variableId.equals(n.toString())) {
                return (n.resolveValue());
            }
        }
        throw new RuntimeException("Unable to resolveValue id: " + variableId);
    }
}
