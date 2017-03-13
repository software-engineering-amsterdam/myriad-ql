package org.uva.taxfree.model.types;

import org.uva.taxfree.gui.QuestionForm;
import org.uva.taxfree.model.node.operators.CompareOperator;
import org.uva.taxfree.model.node.operators.NumericOperator;
import org.uva.taxfree.model.node.operators.BooleanOperator;
import org.uva.taxfree.model.node.widgets.BooleanWidget;

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
}
