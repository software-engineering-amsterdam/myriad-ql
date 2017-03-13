package org.uva.taxfree.model.types;

import org.uva.taxfree.model.node.operators.AddOperator;
import org.uva.taxfree.model.node.operators.AndOperator;
import org.uva.taxfree.model.node.operators.Operator;
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

    @Override
    public boolean supports(AndOperator and) {
        return false;
    }
}
