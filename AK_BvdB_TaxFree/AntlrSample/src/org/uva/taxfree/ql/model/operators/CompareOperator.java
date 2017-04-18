package org.uva.taxfree.ql.model.operators;

import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.types.Type;

public abstract class CompareOperator extends Operator {

    @Override
    public boolean supports(Type left, Type right) {
        return left.supports(this) && right.supports(this);
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}
