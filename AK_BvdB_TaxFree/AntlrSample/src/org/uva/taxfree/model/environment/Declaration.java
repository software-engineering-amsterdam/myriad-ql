package org.uva.taxfree.model.environment;

import org.antlr.v4.codegen.model.decl.Decl;
import org.uva.taxfree.model.node.declarations.DeclarationNode;
import org.uva.taxfree.model.types.Type;

public class Declaration {
    private String mValue;
    private DeclarationNode mNode;

    public Declaration(DeclarationNode node) {
        mNode = node;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
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
