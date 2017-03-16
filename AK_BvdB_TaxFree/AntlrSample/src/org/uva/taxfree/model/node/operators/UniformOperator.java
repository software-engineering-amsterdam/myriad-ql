package org.uva.taxfree.model.node.operators;

import org.uva.taxfree.model.types.BooleanType;
import org.uva.taxfree.model.types.Type;

public class UniformOperator extends Operator {
    @Override
    public boolean supports(Type left, Type right) {
        return true;
    }

    @Override
    public Type getType() {
        return new BooleanType();
    }
}
