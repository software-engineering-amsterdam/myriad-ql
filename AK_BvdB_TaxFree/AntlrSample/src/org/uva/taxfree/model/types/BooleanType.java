package org.uva.taxfree.model.types;

import org.uva.taxfree.model.node.operators.CompareOperator;
import org.uva.taxfree.model.node.operators.NumericOperator;
import org.uva.taxfree.model.node.operators.BooleanOperator;

public class BooleanType extends Type {
    @Override
    public boolean supports(NumericOperator numericOperator) {
        return false;
    }

    @Override
    public boolean supports(BooleanOperator booleanOperator) {
        return true;
    }

    @Override
    public boolean supports(CompareOperator compareOperator) {
        return true;
    }
}
