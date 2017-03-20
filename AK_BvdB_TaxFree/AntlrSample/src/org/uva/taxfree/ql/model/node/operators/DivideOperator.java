package org.uva.taxfree.ql.model.node.operators;

import org.uva.taxfree.ql.model.node.expression.ExpressionNode;

public class DivideOperator extends NumericOperator {
    @Override
    public String evaluate(ExpressionNode left, ExpressionNode right) {
        return 0 == right.asInteger() ? "" : new Integer(left.asInteger() / right.asInteger()).toString();
    }
}
