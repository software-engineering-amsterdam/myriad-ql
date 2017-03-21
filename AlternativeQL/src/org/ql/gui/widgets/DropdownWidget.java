package org.ql.gui.widgets;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;
import org.qls.ast.page.WidgetQuestion;

public class DropdownWidget extends Widget {
    private final ComboBox comboBox;
    private final WidgetBooleanText widgetBooleanText;

    public DropdownWidget(ValueReviser valueReviser, Question question, WidgetBooleanText widgetBooleanText) {
        this.widgetBooleanText = widgetBooleanText;
        comboBox = new ComboBox();
        comboBox.getItems().add(widgetBooleanText.getYesText());
        comboBox.getItems().add(widgetBooleanText.getNoText());
        comboBox.setOnAction(event -> valueReviser.reviseValue(question.getId(), value()));
    }

    private BooleanValue value() {
        return new BooleanValue((comboBox.getValue()).equals(widgetBooleanText.getYesText()));
    }

    @Override
    public void updateWidgetValue(Value value) {
        if (value.toBoolean())
            comboBox.setValue(widgetBooleanText.getYesText());
    }

    @Override
    public Pane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.getChildren().add(comboBox);
        return gridPane;
    }
}
