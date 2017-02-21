package org.uva.taxfree.model;

public class LiteralNode extends Node{
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
