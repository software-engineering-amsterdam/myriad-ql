package org.uva.taxfree.model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class Node {
    private Set<Node> mChildren;

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

    public void printValue() {
        // Intentionally left blank
    }

    // declare abstract to force implementation. default Object.toString() is not declared abstract
    public abstract String toString();

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

    public Set<ConditionNode> getConditionsV2() {
        Set<ConditionNode> nodeSet = new LinkedHashSet<>();
        for (Node n : mChildren) {
            nodeSet.addAll(n.getConditionsV2());
        }

//        By checking with a method that we need to override
//        if (isCondition()) {
//            nodeSet.add(<mCondition>);
//        }
//        By calling a method that we need to override
        nodeSet.addAll(addConditions());
        return nodeSet;
    }

//    protected boolean isCondition() {
//        System.out.println("Condition? " + getClass().toString());
//        return false;
//    }

    protected Set<ConditionNode> addConditions() {
        return Collections.emptySet();
    }

    public Set<LiteralNode> getVariables() {
        Set<LiteralNode> nodeSet = new LinkedHashSet<>();
        for (Node n : mChildren) {
            nodeSet.addAll(n.getVariables());
        }

        nodeSet.addAll(addVariables());
        return nodeSet;
    }

    protected Set<LiteralNode> addVariables() {
        return Collections.emptySet();
    }
}