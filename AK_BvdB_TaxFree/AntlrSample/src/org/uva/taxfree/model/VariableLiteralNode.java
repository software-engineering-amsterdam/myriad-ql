package org.uva.taxfree.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class VariableLiteralNode extends LiteralNode{
    public VariableLiteralNode(String label){
        super(label);
    }

    @Override
    protected Set<LiteralNode> addVariables() {
        Set<LiteralNode> nodeSet = new LinkedHashSet<>();
        nodeSet.add(this);
        return nodeSet;
    }
}
