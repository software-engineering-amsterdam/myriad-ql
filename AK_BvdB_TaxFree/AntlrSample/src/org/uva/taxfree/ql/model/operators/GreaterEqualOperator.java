package org.uva.taxfree.ql.model.operators;

import org.uva.taxfree.ql.model.node.expression.ExpressionNode;

public class GreaterEqualOperator extends CompareOperator {
    @Override
    public String evaluate(ExpressionNode left, ExpressionNode right) {
        return new Boolean(left.asInteger() >=  right.asInteger()).toString();
    }
}
