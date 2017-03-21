package org.uva.taxfree.ql.model.operators;

import org.uva.taxfree.ql.model.node.expression.ExpressionNode;

public class AndOperator extends BooleanOperator {
    @Override
    public String evaluate(ExpressionNode left, ExpressionNode right) {
        return new Boolean(left.asBoolean() && right.asBoolean()).toString();
    }
}
