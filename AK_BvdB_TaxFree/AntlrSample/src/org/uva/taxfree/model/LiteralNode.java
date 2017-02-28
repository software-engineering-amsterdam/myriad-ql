package org.uva.taxfree.model;

public abstract class LiteralNode extends ConditionNode{
    String mId;
    public LiteralNode(String id){
        super();
        mId = id;
    }

    @Override
    public String toString() {
        return mId;
    }

}
