package org.uva.taxfree.model.types;

import org.uva.taxfree.model.node.operators.AddOperator;
import org.uva.taxfree.model.node.operators.OrOperator;

public class DateType extends Type {
    @Override
    public boolean supports(AddOperator add) {
        return false;
    }

    @Override
    public boolean supports(OrOperator or) {
        return false;
    }
}
