package org.uva.taxfree.model.node.expression;

import org.uva.taxfree.model.types.BooleanType;
import org.uva.taxfree.model.types.Type;

public class BooleanExpressionNode extends ExpressionNode {
    public BooleanExpressionNode(ConditionNode left, String operator, ConditionNode right) {
        super(left, operator, right);
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}
