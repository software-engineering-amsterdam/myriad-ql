package org.ql.gui.widgets;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.mediator.GUIMediator;

public class CheckBoxWidget extends Widget {
    private final CheckBox checkBox;

    public CheckBoxWidget(GUIMediator mediator, Question question) {
        checkBox = new CheckBox(question.getQuestionLabel().toString());
        checkBox.setOnAction(event -> mediator.actualizeValue(question.getId(), value()));
    }

    private BooleanValue value() {
        return new BooleanValue(checkBox.isSelected());
    }

    @Override
    public void updateValue(Value value) {
        checkBox.setSelected(value.toBoolean());
    }

    @Override
    public Pane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.getChildren().add(checkBox);
        return gridPane;
    }
}
