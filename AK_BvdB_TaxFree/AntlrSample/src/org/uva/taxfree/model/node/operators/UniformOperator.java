package org.uva.taxfree.model.node.operators;

import org.uva.taxfree.model.types.Type;

public class UniformOperator extends Operator {
    public UniformOperator(String operator) {
        super(operator);
    }

    @Override
    public boolean supports(Type left, Type right) {
        return true;
    }
}
