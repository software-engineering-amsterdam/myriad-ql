package com.matthewchapman.ql.gui.widgets;

import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.values.Value;

/**
 * Created by matt on 22/03/2017.
 */
public class IntegerQuestionWidget extends QuestionWidget {

    IntegerQuestionWidget(Question question, Value value) {
        super(question, value);
    }
}
