package org.uva.taxfree.model.types;

import org.uva.taxfree.gui.QuestionForm;
import org.uva.taxfree.model.node.operators.BooleanOperator;
import org.uva.taxfree.model.node.operators.CompareOperator;
import org.uva.taxfree.model.node.operators.NumericOperator;

public abstract class Type {
    public boolean equals(Type other) {
        return getClass().equals(other.getClass());
    }

    public abstract boolean supports(CompareOperator compareOperator);

    public abstract boolean supports(BooleanOperator booleanOperator);

    public abstract boolean supports(NumericOperator numericOperator);

    public abstract void generateWidget(String label, String id, QuestionForm frame);
}
