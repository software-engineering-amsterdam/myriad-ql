package org.ql.gui.widgets;


import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.BooleanValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;
import org.qls.ast.page.WidgetQuestion;

public class RadioButtonWidget extends Widget {
    final ToggleGroup buttonGroup = new ToggleGroup();
    private final RadioButton yesButton;
    private final RadioButton noButton;

    public RadioButtonWidget(ValueReviser valueReviser, Question question, String yesText, String noText) {
        yesButton = createRadioButton(valueReviser, question, yesText);
        noButton = createRadioButton(valueReviser, question, noText);
    }

    private RadioButton createRadioButton(ValueReviser valueReviser, Question question, String text) {
        RadioButton button = new RadioButton(text);
        button.setOnAction(event -> valueReviser.reviseValue(question.getId(), new BooleanValue(true)));
        button.setToggleGroup(buttonGroup);
        return button;
    }

    @Override
    public void updateWidgetValue(Value value) {
        yesButton.setSelected(value.toBoolean());
        noButton.setSelected(!value.toBoolean());
    }

    @Override
    public Pane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.add(yesButton, 0, 1);
        gridPane.add(noButton, 0, 0);
        return gridPane;
    }
}