package org.uva.taxfree.model;

public class FormNode extends Node {
    private String mId;
    public FormNode(String id, Node parent) {
        super(parent);
        mId = id;
    }

    public String getId(){
        return mId;
    }

}
