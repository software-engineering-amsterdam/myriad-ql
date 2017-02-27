package org.uva.taxfree.model;

public class Symbol {
    private String mId;
    private Node mReference;

    public void Symbol(String id, Node reference) {
        mId = id;
        mReference = reference;
    }

    public String toString() {
        return mReference.toString();
    }
}
