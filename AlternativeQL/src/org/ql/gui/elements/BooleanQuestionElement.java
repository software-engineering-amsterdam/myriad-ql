package org.ql.gui.elements;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.ql.ast.statement.Question;
import org.ql.gui.mediator.GUIMediator;
import org.ql.gui.widgets.CheckBoxWidget;
import org.ql.gui.widgets.Widget;

public class BooleanQuestionElement extends QuestionElement {
    public BooleanQuestionElement(GUIMediator mediator, Question question, CheckBoxWidget widget) {
        super(mediator, question, widget);
    }
}
