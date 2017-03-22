package com.matthewchapman.ql.gui.widgets;

import com.matthewchapman.ql.ast.atomic.Type;
import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.values.Value;
import javafx.scene.layout.GridPane;

/**
 * Created by matt on 17/03/2017.
 */
public abstract class QuestionWidget extends GridPane {

    private String id;
    private Type returnType;
    private String label;

    QuestionWidget(Question question, Value value) {
        this.id = question.getName();
        this.returnType = question.getType();
        this.label = question.getText();
    }
}
