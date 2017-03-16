package org.uva.taxfree.model.types;

import org.uva.taxfree.gui.QuestionForm;
import org.uva.taxfree.gui.widgets.IntegerWidget;
import org.uva.taxfree.model.node.operators.BooleanOperator;
import org.uva.taxfree.model.node.operators.CompareOperator;
import org.uva.taxfree.model.node.operators.NumericOperator;

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

    @Override
    public void generateWidget(String label, String id, QuestionForm frame) {
        frame.addWidget(new IntegerWidget(label, id));
    }

    @Override
    public String defaultValue() {
        return "0";
    }
}
