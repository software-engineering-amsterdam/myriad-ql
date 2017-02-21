package org.uva.taxfree.model;

public class LiteralNode extends ExpressionNode{
    String mId;
    public LiteralNode(String id){
        super(id);
        mId = id;
    }

    @Override
    public String toString() {
        return mId;
    }

}
