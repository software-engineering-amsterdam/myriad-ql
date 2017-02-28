package org.uva.taxfree.model;

public abstract class LiteralNode extends ConditionNode {
    private final String mId;

    public LiteralNode(String id) {
        super();
        mId = id;
    }

    @Override
    public String toString() {
        return resolve();
    }

    public String resolve() {
        return mId;
    }

}
