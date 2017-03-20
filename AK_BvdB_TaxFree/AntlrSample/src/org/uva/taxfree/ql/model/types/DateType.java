package org.uva.taxfree.ql.model.types;

import org.uva.taxfree.ql.gui.QuestionForm;
import org.uva.taxfree.ql.gui.widgets.DateWidget;
import org.uva.taxfree.ql.model.node.operators.BooleanOperator;
import org.uva.taxfree.ql.model.node.operators.CompareOperator;
import org.uva.taxfree.ql.model.node.operators.NumericOperator;

public class DateType extends Type {
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
        return true;
    }

    @Override
    public void generateWidget(String label, String id, QuestionForm frame) {
        frame.addWidget(new DateWidget(label, id));
    }

    @Override
    public String defaultValue() {
        return "01-01-1900";
    }
}
