package org.uva.taxfree.model.node.operators;

import org.uva.taxfree.model.types.Type;

public abstract class Operator {
    private final String mOperator;

    public Operator(String operator) {
        mOperator = operator;
    }

    public String resolveValue() {
        return mOperator;
    }

    public abstract boolean supports(Type left, Type right);

    public abstract Type getType();
}
