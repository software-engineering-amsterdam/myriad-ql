package org.ql.gui.widgets;

import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.ast.statement.ComputableQuestion;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;

abstract class SpinnerWidget<V> extends Widget {
    private final Spinner<V> spinner;
    private final Label label;

    public SpinnerWidget(ValueReviser mediator, Question question, SpinnerValueFactory<V> valueFactory) {
        label = new Label(question.getLabel().toString());
        spinner = new Spinner<>();
        spinner.setValueFactory(valueFactory);
        spinner.setOnMouseClicked(event -> reviseValue(mediator, question));
        spinner.setOnKeyReleased(event -> reviseValue(mediator, question));
    }

    private void reviseValue(ValueReviser mediator, Question question) {
        mediator.reviseValue(question.getId(), createValue(spinner));
    }

    @Override
    public Pane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.add(label, 0, 0);
        gridPane.add(spinner, 1, 0);
        return gridPane;
    }

    public void updateWidgetValue(Value value) {
        spinner.getValueFactory().setValue(extractSpinnerValue(value));
    }

    protected abstract V extractSpinnerValue(Value value);

    protected abstract Value createValue(Spinner<V> spinner);
}
