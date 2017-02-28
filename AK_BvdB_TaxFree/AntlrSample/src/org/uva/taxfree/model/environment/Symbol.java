package org.uva.taxfree.model.environment;

import org.uva.taxfree.model.node.statement.NamedNode;

public class Symbol {
    private String mId;
    private NamedNode mReference;

    public Symbol(String id, NamedNode reference) {
        mId = id;
        mReference = reference;
    }

    public String toString() {
        return mReference.toString();
    }

    public String resolveValue() {
        return mReference.resolveValue();
    }
}
