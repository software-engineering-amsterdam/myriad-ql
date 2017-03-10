package org.ql.gui.elements;

import org.ql.ast.Identifier;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.IntegerValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.CheckBoxWidget;
import org.ql.gui.widgets.NumericWidget;

public class IntegerQuestionElement extends QuestionElement {
    public IntegerQuestionElement(GUIMediator mediator, Identifier identifier, NumericWidget widget) {
        super(mediator, identifier, widget);
    }

    @Override
    public Value getValue() {
        return new IntegerValue((Integer) getWidget().getValue());
    }
}
