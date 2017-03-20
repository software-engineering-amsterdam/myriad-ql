package org.uva.taxfree.model.environment;

import org.uva.taxfree.model.node.declarations.DeclarationNode;
import org.uva.taxfree.model.types.Type;

public class Declaration {
    private Value mValue;
    private DeclarationNode mNode;

    public Declaration(DeclarationNode node) {
        mValue = new NullValue();
        mNode = node;
    }


    public String getValue() {
        return mValue.getValue();
    }

    public void setValue(boolean boolValue) {
        if (mValue.isUninitialized()) {
            mValue = new BoolValue(boolValue);
        }
        mValue.setValue(boolValue);
    }

    public void setValue(int intValue) {
        if (mValue.isUninitialized()) {
            mValue = new IntValue(intValue);
        }
        mValue.setValue(intValue);
    }

    public void setValue(String stringValue) {
        if (mValue.isUninitialized()) {
            mValue = new StringValue(stringValue);
        }
        mValue.setValue(stringValue);

    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(toString());
    }

    @Override
    public String toString() {
        return getId();
    }

    public String getId() {
        return mNode.getId();
    }

    public String getLabel() {
        return mNode.getLabel();
    }

    public Type getType() {
        return mNode.getType();
    }

}
