package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.node.condition.ConditionNode;
import org.uva.taxfree.model.node.Node;

public class ExpressionNode extends ConditionNode {
    private ConditionNode mLeft;
    private String mOperator;
    private ConditionNode mRight;

    public ExpressionNode(String operator) {
        mOperator = operator;
    }

    @Override
    public void addChild(Node node) {
        if (mLeft == null) {
            mLeft = (ConditionNode) node;
        } else if (mRight == null) {
            mRight = (ConditionNode) node;
        } else {
            // Error handling!
        }
    }

    @Override
    public String toString() {
        return resolve();
    }

    @Override
    public String resolve() {
        return "(" + mLeft.resolve() + mOperator + mRight.resolve() + ")";
    }

}
