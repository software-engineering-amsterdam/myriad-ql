package org.uva.taxfree.model.node;

import org.uva.taxfree.model.node.statement.NamedNode;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Node {
    private final Set<Node> mChildren;

    public Node() {
        mChildren = new LinkedHashSet<>(); ///< preserves the order in which the items were inserted
    }

    public void addChild(Node child) {
        mChildren.add(child);
    }

    public void retrieveQuestions(Set<NamedNode> set) {
        addQuestion(set);
        for (Node child : mChildren) {
            child.retrieveQuestions(set);
        }
    }

    protected void addQuestion(Set<NamedNode> set) {
        // Intentionally left blank
    }

    public void printData() {
        printValue();
        for (Node child : mChildren) {
            child.printValue();
        }
    }

    public void setVisibility(boolean isVisible) {
        for (Node child : mChildren) {
            child.setVisibility(isVisible);
        }
    }

    protected void printValue() {
        // Intentionally left blank
    }

    public void printAll() {
        System.out.println(toString());
        for (Node child : mChildren) {
            child.printAll();
        }
    }

    public void retrieveConditions(Set<Node> set) {
        addCondition(set);
        for (Node child : mChildren) {
            child.retrieveConditions(set);
        }
    }

    protected void addCondition(Set<Node> set) {
        // Intentionally left blank
    }
}