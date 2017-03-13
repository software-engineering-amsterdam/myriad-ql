package org.uva.taxfree.model.types;

import org.uva.taxfree.model.node.operators.CompareOperator;
import org.uva.taxfree.model.node.operators.NumericOperator;
import org.uva.taxfree.model.node.operators.BooleanOperator;

public class IntegerType extends Type {
    @Override
    public boolean supports(NumericOperator numericOperator) {
        return true;
    }

    @Override
    public boolean supports(BooleanOperator booleanOperator) {
        return false;
    }

    @Override
    public boolean supports(CompareOperator compareOperator) {
        return true;
    }
}
