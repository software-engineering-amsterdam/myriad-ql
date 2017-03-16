package org.uva.taxfree.model.node.operators;

import org.uva.taxfree.model.node.expression.ExpressionNode;

public class AddOperator extends NumericOperator {
    @Override
    public String evaluate(ExpressionNode left, ExpressionNode right) {
        return new Integer(left.asInteger() + right.asInteger()).toString();
    }
}
