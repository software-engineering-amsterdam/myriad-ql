package com.matthewchapman.ql.gui.widgets;

import com.matthewchapman.ql.ast.statement.Question;
import com.matthewchapman.ql.environment.values.BooleanValue;
import com.matthewchapman.ql.environment.values.Value;
import com.matthewchapman.ql.gui.QuestionChangeObserver;
import javafx.geometry.HPos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

/**
 * Created by matt on 22/03/2017.
 */
public class BooleanQuestionWidget extends QuestionWidget {

    private final CheckBox answer;

    public BooleanQuestionWidget(Question question, Value value, QuestionChangeObserver observer) {
        super(question, observer);

        answer = new CheckBox();
        answer.setOnMouseClicked(event -> observer.notifyQuestionChanged(super.getQuestionID(), new BooleanValue(answer.isSelected())));

        answer.setSelected(Boolean.valueOf(value.toString()));
        this.add(answer, 1, 0);
        GridPane.setHalignment(answer, HPos.LEFT);
    }

    @Override
    public void setEditable(boolean value) {
        answer.setDisable(value);
    }

}
