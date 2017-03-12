package org.uva.taxfree.model.types;

import org.uva.taxfree.model.node.operators.AddOperator;

public class BooleanType extends Type {
    @Override
    public boolean supports(AddOperator add) {
        return false;
    }
}
