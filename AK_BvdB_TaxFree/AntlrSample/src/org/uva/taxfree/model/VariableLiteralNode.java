package org.uva.taxfree.model;

public class VariableLiteralNode extends LiteralNode{
    private String mLabel;
    public VariableLiteralNode(String label){
        super(label);
        mLabel = label;
    }

}
