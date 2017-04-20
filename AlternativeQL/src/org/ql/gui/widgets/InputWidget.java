package org.ql.gui.widgets;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.Value;
import org.ql.gui.FormPane;
import org.ql.gui.ValueReviser;

abstract class InputWidget extends GUIWidget {
    private final Label label;
    private final TextField textField;


    InputWidget(ValueReviser valueReviser, Question question) {
        label = new Label(question.getLabel().toString());
        textField = new TextField();
        textField.setOnKeyReleased(event -> valueReviser.reviseValue(question.getId(), value(textField.getText())));
    }

    protected abstract Value value(String textFieldText);

    @Override
    public void updateWidgetValue(Value value) {
        textField.setText(value.toString());
    }

    @Override
    public Pane createGridPane() {
        FormPane gridPane = new FormPane();
        gridPane.add(label, 0, 0);
        gridPane.add(textField, 1, 0);
        return gridPane;
    }
}
