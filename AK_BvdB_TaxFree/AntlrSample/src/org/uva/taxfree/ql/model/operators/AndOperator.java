package org.uva.taxfree.ql.model.operators;

import org.uva.taxfree.ql.model.node.expression.ExpressionNode;
import org.uva.taxfree.ql.model.values.Value;

public class AndOperator extends BooleanOperator {
    @Override
    public Value evaluate(ExpressionNode left, ExpressionNode right) {
        return left.evaluate().logicalAnd(right.evaluate());
    }
}
