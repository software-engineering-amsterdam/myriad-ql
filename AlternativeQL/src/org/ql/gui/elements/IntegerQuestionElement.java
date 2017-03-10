package org.ql.gui.elements;

import org.ql.ast.statement.Question;
import org.ql.evaluator.value.IntegerValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.NumericWidget;

public class IntegerQuestionElement extends QuestionElement {
    public IntegerQuestionElement(GUIMediator mediator, Question question, NumericWidget widget) {
        super(mediator, question, widget);
    }

    @Override
    public Value getValue() {
        return new IntegerValue((Integer) getWidget().getValue());
    }
}
