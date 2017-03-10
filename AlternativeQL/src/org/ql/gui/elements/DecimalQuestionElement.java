package org.ql.gui.elements;

import org.ql.ast.statement.Question;
import org.ql.evaluator.value.DecimalValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.NumericWidget;

import java.math.BigDecimal;

public class DecimalQuestionElement extends QuestionElement {
    public DecimalQuestionElement(GUIMediator mediator, Question question, NumericWidget widget) {
        super(mediator, question, widget);
    }

    @Override
    public Value getValue() {
        return new DecimalValue(new BigDecimal((String) getWidget().getValue()));
    }
}
