package com.matthewchapman.ql.gui;

import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.values.Value;
import com.matthewchapman.ql.gui.widgets.BooleanQuestionWidget;
import com.matthewchapman.ql.gui.widgets.IntegerQuestionWidget;
import com.matthewchapman.ql.gui.widgets.QuestionWidget;
import com.matthewchapman.ql.gui.widgets.StringQuestionWidget;

/**
 * Created by matt on 22/03/2017.
 */
public class QuestionWidgetFactory {

    public QuestionWidget getQuestionWidget(Question question, Value value, QuestionChangeObserver observer) {

        switch(question.getType().toString()) {
            case "boolean":
                return new BooleanQuestionWidget(question, value, observer);
            case "string":
                return new StringQuestionWidget(question, value, observer);
            case "integer":
                return new IntegerQuestionWidget(question, value, observer);
            default:
                throw new RuntimeException();
        }
    }
}
