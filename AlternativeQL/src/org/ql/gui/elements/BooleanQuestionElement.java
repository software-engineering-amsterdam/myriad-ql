package org.ql.gui.elements;

import org.ql.ast.statement.Question;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.widgets.Widget;

public class BooleanQuestionElement extends QuestionElement {

    private BooleanValue booleanValue;

    public BooleanQuestionElement(Question question, Widget widget) {
        super(question, widget);
    }

    @Override
    public Value getValue() {
        return booleanValue;
    }

    @Override
    public void setValue(Value value) {
        booleanValue = (BooleanValue) value;
    }
}
