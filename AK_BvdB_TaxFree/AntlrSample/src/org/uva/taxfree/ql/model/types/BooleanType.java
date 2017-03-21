package org.uva.taxfree.ql.model.types;

import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.gui.widgets.BooleanWidget;
import org.uva.taxfree.ql.model.operators.BooleanOperator;
import org.uva.taxfree.ql.model.operators.CompareOperator;
import org.uva.taxfree.ql.model.operators.NumericOperator;
import org.uva.taxfree.ql.model.values.BooleanValue;
import org.uva.taxfree.ql.model.values.Value;

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

    @Override
    public void generateWidget(String label, String id, QuestionForm frame) {
        frame.addWidget(new BooleanWidget(label, id));
    }

    @Override
    public Value defaultValue() {
        return new BooleanValue(false);
    }

    @Override
    public String toString() {
        return "Boolean";
    }
}
