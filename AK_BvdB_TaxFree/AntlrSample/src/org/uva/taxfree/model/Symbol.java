package org.uva.taxfree.model;

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

    public String resolve() {
        return mReference.resolve();
    }
}
