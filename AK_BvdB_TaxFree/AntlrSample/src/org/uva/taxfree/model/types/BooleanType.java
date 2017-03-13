package org.uva.taxfree.model.types;

import org.uva.taxfree.model.node.operators.AddOperator;
import org.uva.taxfree.model.node.operators.AndOperator;
import org.uva.taxfree.model.node.operators.OrOperator;

public class BooleanType extends Type {
    @Override
    public boolean supports(AddOperator add) {
        return false;
    }

    @Override
    public boolean supports(OrOperator or) {
        return true;
    }

    @Override
    public boolean supports(AndOperator and) {
        return true;
    }
}
