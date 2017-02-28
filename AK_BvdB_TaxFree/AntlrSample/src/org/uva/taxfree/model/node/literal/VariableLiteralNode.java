package org.uva.taxfree.model.node.literal;


import org.uva.taxfree.model.node.statement.NamedNode;

public class VariableLiteralNode extends LiteralNode {
    private NamedNode mReference;

    public VariableLiteralNode(String label) {
        super(label);
    }

    public void setReference(NamedNode reference) {
        mReference = reference;
    }

    @Override
    public String resolve() {
        return mReference.resolve();
    }
}
