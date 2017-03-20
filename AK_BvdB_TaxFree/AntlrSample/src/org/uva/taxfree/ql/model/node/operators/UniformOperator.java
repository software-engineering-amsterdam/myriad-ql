package org.uva.taxfree.ql.model.node.operators;

import org.uva.taxfree.ql.model.types.BooleanType;
import org.uva.taxfree.ql.model.types.Type;

public abstract class UniformOperator extends Operator {
    @Override
    public boolean supports(Type left, Type right) {
        return true;
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}
