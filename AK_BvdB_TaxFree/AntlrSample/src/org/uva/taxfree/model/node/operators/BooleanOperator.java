package org.uva.taxfree.model.node.operators;

import org.uva.taxfree.model.types.BooleanType;
import org.uva.taxfree.model.types.Type;

public class BooleanOperator extends Operator {
    public BooleanOperator(String operator) {
        super(operator);
    }

    @Override
    public boolean supports(Type left, Type right) {
        boolean isSupported = left.supports(this) && right.supports(this);
        return isSupported;
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}
