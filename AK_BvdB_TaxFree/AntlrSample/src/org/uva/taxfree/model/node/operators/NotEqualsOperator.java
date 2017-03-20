package org.uva.taxfree.model.node.operators;

import org.uva.taxfree.model.node.expression.ExpressionNode;

public class NotEqualsOperator extends UniformOperator {
    @Override
    public String evaluate(ExpressionNode left, ExpressionNode right) {
        return new Boolean(!left.asString().equals(right.asString())).toString();
    }
}
