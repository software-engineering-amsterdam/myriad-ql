package org.uva.taxfree.ql.model.environment;

import org.uva.taxfree.ql.model.node.declarations.DeclarationNode;
import org.uva.taxfree.ql.model.types.Type;

public class Declaration {
    private String mValue;
    private DeclarationNode mNode;

    protected Declaration(DeclarationNode node) {
        mValue = "";
        mNode = node;
    }

    protected String getValue() {
        return mValue.isEmpty() ? mNode.defaultValue() : mValue;
    }

    protected void setValue(String value) {
        mValue = value;
    }

    protected String getId() {
        return mNode.getId();
    }

    protected String getLabel() {
        return mNode.getLabel();
    }

    protected Type getType() {
        return mNode.getType();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(toString());
    }

    @Override
    public String toString() {
        return getId();
    }

}
