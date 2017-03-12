package org.uva.taxfree.model.node.operators;

import org.uva.taxfree.model.types.Type;

public class AddOperator extends Operator {
    @Override
    public boolean supports(Type left, Type right) {
        return left.supports(this) && right.supports(this);
    }
}
