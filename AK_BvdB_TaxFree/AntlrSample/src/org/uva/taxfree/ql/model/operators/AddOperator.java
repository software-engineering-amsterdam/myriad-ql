package org.uva.taxfree.ql.model.operators;

import org.uva.taxfree.ql.model.node.expression.ExpressionNode;

public class AddOperator extends NumericOperator {
    @Override
    public String evaluate(ExpressionNode left, ExpressionNode right) {
        return new Integer(left.asInteger() + right.asInteger()).toString();
    }
}
