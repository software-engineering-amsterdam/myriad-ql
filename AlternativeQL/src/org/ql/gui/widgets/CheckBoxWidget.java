package org.ql.gui.widgets;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;

public class CheckBoxWidget extends Widget {
    private final CheckBox checkBox;

    public CheckBoxWidget(ValueReviser mediator, Question question) {
        checkBox = new CheckBox(question.getQuestionLabel().toString());
        checkBox.setOnAction(event -> mediator.reviseValue(question.getId(), value()));
    }

    private BooleanValue value() {
        return new BooleanValue(checkBox.isSelected());
    }

    @Override
    public void updateWidgetValue(Value value) {
        checkBox.setSelected(value.toBoolean());
    }

    @Override
    public Pane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.getChildren().add(checkBox);
        return gridPane;
    }
}
