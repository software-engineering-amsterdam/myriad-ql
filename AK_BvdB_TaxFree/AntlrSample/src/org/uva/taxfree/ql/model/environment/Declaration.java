package org.uva.taxfree.ql.model.environment;

import org.uva.taxfree.ql.model.node.declarations.DeclarationNode;
import org.uva.taxfree.ql.model.types.Type;
import org.uva.taxfree.ql.model.values.Value;

public class Declaration {
    private Value mValue;
    private DeclarationNode mNode;

    protected Declaration(DeclarationNode node) {
        mNode = node;
        mValue = node.generateValue();
    }

    protected Value getValue() {
        return mValue;
    }

    protected void setValue(Value value) {
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
