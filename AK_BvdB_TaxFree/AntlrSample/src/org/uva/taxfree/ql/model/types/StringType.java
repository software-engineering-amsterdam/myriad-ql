package org.uva.taxfree.ql.model.types;

import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.gui.widgets.StringWidget;
import org.uva.taxfree.ql.model.operators.BooleanOperator;
import org.uva.taxfree.ql.model.operators.CompareOperator;
import org.uva.taxfree.ql.model.operators.NumericOperator;

public class StringType extends Type {
    @Override
    public boolean supports(NumericOperator numericOperator) {
        return false;
    }

    @Override
    public boolean supports(BooleanOperator booleanOperator) {
        return false;
    }

    @Override
    public boolean supports(CompareOperator compareOperator) {
        return false;
    }

    @Override
    public void generateWidget(String label, String id, QuestionForm frame) {
        frame.addWidget(new StringWidget(label, id));
    }

    @Override
    public String defaultValue() {
        return "None...";
    }
}
