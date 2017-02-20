package org.uva.taxfree.model;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Node {

    private Node mParent;
    private Set<Node> mChildren;

    public Node() {
        mChildren = new LinkedHashSet<>(); ///< preserves the order in which the items were inserted
    }

    public void addChild(Node child) {
        mChildren.add(child);
    }

    public abstract String getId();

    public void retrieveQuestions(Set<NamedNode> set) {
        addQuestion(set);
        for (Node child : mChildren) {
            child.addQuestion(set);
        }
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


    protected void addQuestion(Set<NamedNode> set) {
        // Intentionally left blank
    }


    public void printValue() {
        // Intentionally left blank
    }

    public abstract String getType();
}