package org.uva.taxfree.model.node.blocks;

import org.uva.taxfree.model.node.Node;

import java.util.Set;

public class FormNode extends BlockNode {
    private final String mFormName;

    public FormNode(String label, Set<Node> children) {
        super(children);
        mFormName = label;
    }

    @Override
    public String toString() {
        return mFormName;
    }

    @Override
    protected boolean isVisible() {
        return true;
    }

}
