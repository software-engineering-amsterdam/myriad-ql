package org.ql.gui.widgets;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.Value;
import org.ql.gui.mediator.GUIMediator;

abstract class SpinnerWidget<V> extends Widget {
    private final Spinner<V> spinner;
    private final Label label;

    SpinnerWidget(GUIMediator mediator, Question question) {
        label = new Label(question.getQuestionLabel().toString());
        spinner = new Spinner<>();
        spinner.setValueFactory(createSpinnerValueFactory());
        spinner.setOnMouseClicked(eventHandler(mediator, question));
    }

    private EventHandler<MouseEvent> eventHandler(GUIMediator mediator, Question question) {
        return event -> mediator.actualizeValue(question.getId(), createValue(spinner.getValue()));
    }

    @Override
    public Pane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.add(label, 0, 0);
        gridPane.add(spinner, 1, 0);
        return gridPane;
    }

    protected abstract Value createValue(V value);

    protected abstract SpinnerValueFactory<V> createSpinnerValueFactory();
}
