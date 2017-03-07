package org.uva.taxfree.model.node.blocks;

import org.uva.taxfree.model.node.Node;
import org.uva.taxfree.model.node.declarations.CalculatedField;
import org.uva.taxfree.model.node.declarations.NamedNode;
import org.uva.taxfree.model.node.expression.ExpressionNode;

import java.util.Set;

public abstract class BlockNode extends Node {
    private final Set<Node> mChildren;

    public BlockNode(Set<Node> children) {
        mChildren = children; ///< preserves the order in which the items were inserted
    }

    public void printData() {
        printValue();
        for (Node child : mChildren) {
            child.printValue();
        }
    }

    public void setVisible(boolean isVisible) {
        for (Node child : mChildren) {
            child.setVisible(isVisible);
        }
    }

    protected abstract boolean isVisible();

    public void retrieveDeclarations(Set<NamedNode> set) {
        addDeclaration(set);
    }

    public void retrieveConditions(Set<ExpressionNode> set) {
        addCondition(set);
    }

    public void retrieveCalculations(Set<CalculatedField> set) {
        addCalculation(set);
    }

    @Override
    public void addCondition(Set<ExpressionNode> set) {
        for (Node child : mChildren) {
            child.addCondition(set);
        }
    }

    @Override
    public void addDeclaration(Set<NamedNode> set) {
        for (Node child : mChildren) {
            child.addDeclaration(set);
        }
    }

    @Override
    public void addCalculation(Set<CalculatedField> set) {
        for (Node child : mChildren) {
            child.addCalculation(set);
        }
    }

    public void printDeclarations() {
        for (Node child : mChildren) {
            child.printId();
        }
    }

    @Override
    public void printValue() {
        for (Node child : mChildren) {
            child.printValue();
        }
    }

    @Override
    public void printId() {
        for (Node child : mChildren) {
            child.printId();
        }
    }
}
