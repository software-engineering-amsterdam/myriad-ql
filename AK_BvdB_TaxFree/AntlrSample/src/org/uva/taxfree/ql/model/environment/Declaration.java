package org.uva.taxfree.ql.model.environment;

import org.uva.taxfree.ql.model.node.declarations.DeclarationNode;
import org.uva.taxfree.ql.model.types.Type;

public class Declaration {
    private String mValue;
    private DeclarationNode mNode;

    public Declaration(DeclarationNode node) {
        mValue = "";
        mNode = node;
    }

    public String getValue() {
        return mValue.isEmpty() ? mNode.defaultValue() : mValue;
    }

    public void setValue(String value) {
        mValue = value;
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
