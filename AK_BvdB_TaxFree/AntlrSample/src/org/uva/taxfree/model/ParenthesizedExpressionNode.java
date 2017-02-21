package org.uva.taxfree.model;

public class ParenthesizedExpressionNode extends ConditionNode {
    private Node mChild;

    public ParenthesizedExpressionNode(String label) {
        super();
    }

    @Override
    public void addChild(Node child) {
        if (mChild == null) {
            mChild = child;
        } else {
            throw new RuntimeException("Multiple children in parentheses");
        }
    }

    @Override
    public String toString() {
        return "(" + mChild.toString() + ")" ;
    }
}
