package org.ql.gui.widgets;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.FormPane;
import org.ql.gui.ValueReviser;

public class CheckboxWidget extends GUIWidget {
    private final CheckBox checkBox;

    public CheckboxWidget(ValueReviser valueReviser, Question question) {
        checkBox = new CheckBox(question.getLabel().toString());
        checkBox.setOnAction(event -> valueReviser.reviseValue(question.getId(), value()));
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
        FormPane gridPane = new FormPane();
        gridPane.getChildren().add(checkBox);
        return gridPane;
    }
}
