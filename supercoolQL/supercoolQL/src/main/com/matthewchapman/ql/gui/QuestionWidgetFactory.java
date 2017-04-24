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
class QuestionWidgetFactory {

    QuestionWidget getQuestionWidget(Question question, Value value, QuestionChangeObserver observer) {
        if ("BOOLEAN".equals(value.getTypeAsString())) {
            return new BooleanQuestionWidget(question, value, observer);
        } else if ("STRING".equals(value.getTypeAsString())) {
            return new StringQuestionWidget(question, value, observer);
        } else {
            return new IntegerQuestionWidget(question, value, observer);
        }
    }
}
