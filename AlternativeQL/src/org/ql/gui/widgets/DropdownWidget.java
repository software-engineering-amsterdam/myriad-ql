package org.ql.gui.widgets;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.FormPane;
import org.ql.gui.ValueReviser;
import org.qls.ast.page.WidgetQuestion;
import org.qls.ast.widget.YesNoWidget;

public class DropdownWidget extends Widget {
    private final ComboBox<String> comboBox;
    private final YesNoWidget widgetBooleanText;

    public DropdownWidget(ValueReviser valueReviser, Question question, YesNoWidget yesNoWidget) {
        this.widgetBooleanText = yesNoWidget;
        comboBox = new ComboBox<>();
        comboBox.getItems().add(yesNoWidget.getYesText());
        comboBox.getItems().add(yesNoWidget.getNoText());
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
        FormPane gridPane = new FormPane();
        gridPane.getChildren().add(comboBox);
        return gridPane;
    }
}
