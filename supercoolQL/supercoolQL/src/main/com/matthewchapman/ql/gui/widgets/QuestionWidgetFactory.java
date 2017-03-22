package com.matthewchapman.ql.gui.widgets;

import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.values.Value;

/**
 * Created by matt on 22/03/2017.
 */
public class QuestionWidgetFactory {

    public QuestionWidget getQuestionWidget(Question question, Value value) {

        switch(question.getType().toString()) {
            case "boolean":
                return new BooleanQuestionWidget(question, value);
            case "string":
                return new StringQuestionWidget(question, value);
            case "integer":
                return new IntegerQuestionWidget(question, value);
            default:
                throw new RuntimeException();
        }
    }
}
