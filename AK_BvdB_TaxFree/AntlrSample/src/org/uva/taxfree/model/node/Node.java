package org.uva.taxfree.model.node;

import org.uva.taxfree.model.node.declarations.CalculatedField;
import org.uva.taxfree.model.node.declarations.NamedNode;

import java.util.Set;

public abstract class Node {
    public void setVisible(boolean isVisible) {
        // Intentionally left blank
    }

    public void printValue() {
        // Intentionally left blank
    }

    public void addCondition(Set<Node> set) {
        // Intentionally left blank
    }

    // Why can't we access this when it's protected and it's a child
    public void addDeclaration(Set<NamedNode> set) {
        // Intentionally left blank
    }

    public void addCalculation(Set<CalculatedField> set) {
        // Intentionally left blank;
    }

    public void printId() {
        // Intentionally left blank;
    }
}