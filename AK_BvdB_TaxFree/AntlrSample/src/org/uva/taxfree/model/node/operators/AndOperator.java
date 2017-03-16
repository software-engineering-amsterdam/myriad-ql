package org.uva.taxfree.model.node.operators;

import org.uva.taxfree.model.node.expression.ExpressionNode;

public class AndOperator extends BooleanOperator {
    @Override
    public String evaluate(ExpressionNode left, ExpressionNode right) {
        return new Boolean(left.asBoolean() && right.asBoolean()).toString();
    }
}
