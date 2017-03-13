package org.uva.taxfree.model.types;

import org.uva.taxfree.model.node.operators.AddOperator;
import org.uva.taxfree.model.node.operators.OrOperator;

public class IntegerType extends Type {
    @Override
    public boolean supports(AddOperator add) {
        return true;
    }

    @Override
    public boolean supports(OrOperator or) {
        return false;
    }
}
