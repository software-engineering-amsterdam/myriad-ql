package org.ql.gui.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.ql.ast.statement.Question;
import org.ql.gui.GUIHandler;
import org.ql.gui.GUIHandlerSingleton;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.NumericWidget;
import org.ql.gui.widgets.Widget;

public class NumericQuestionElement extends QuestionElement {
    public NumericQuestionElement(GUIMediator mediator, Question question, NumericWidget widget) {
        super(mediator, question, widget);
    }
}
