package org.uva.taxfree.model.environment;

import org.uva.taxfree.model.node.statement.NamedNode;

import java.util.LinkedHashSet;
import java.util.Set;

public class SymbolTable {
    private Set<Symbol> mSymbols;

    public SymbolTable() {
        mSymbols = new LinkedHashSet<>();
    }

    public void addSymbol(String hasSoldhouse, NamedNode node) {
        mSymbols.add(new Symbol("hasSoldHouse", node));
    }

    public String resolve(String variableId) {
        for (Symbol s : mSymbols) {
            if (variableId.equals(s.toString())) {
                return s.resolveValue();
            }
        }
        throw new RuntimeException("Unable to resolveValue id: " + variableId);
    }
}
