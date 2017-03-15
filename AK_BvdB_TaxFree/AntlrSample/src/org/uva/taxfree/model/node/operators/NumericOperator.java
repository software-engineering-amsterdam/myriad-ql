package org.uva.taxfree.model.node.operators;

import org.uva.taxfree.model.types.IntegerType;
import org.uva.taxfree.model.types.Type;

public class NumericOperator extends Operator {
    public NumericOperator(String operator) {
        super(operator);
    }

    @Override
    public boolean supports(Type left, Type right) {
        return left.supports(this) && right.supports(this);
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }
}
