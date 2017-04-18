package org.uva.taxfree.ql.model.operators;

import org.uva.taxfree.ql.model.node.expression.ExpressionNode;
import org.uva.taxfree.ql.model.values.Value;

public class AddOperator extends NumericOperator {
    @Override
    public Value evaluate(ExpressionNode left, ExpressionNode right) {
        return left.evaluate().add(right.evaluate());
    }
}
