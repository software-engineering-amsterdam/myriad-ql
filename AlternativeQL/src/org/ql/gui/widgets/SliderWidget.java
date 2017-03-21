package org.ql.gui.widgets;

import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.DecimalValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;
import org.qls.ast.page.WidgetQuestion;

import java.math.BigDecimal;

public abstract class SliderWidget extends Widget {
    private final Slider slider;

    public SliderWidget(ValueReviser valueReviser, Question question) {
        slider = new Slider(0, 100, 50);
        slider.setOnMouseClicked(event -> reviseValue(valueReviser, question.getId()));
        slider.setOnKeyReleased(event -> reviseValue(valueReviser, question.getId()));
    }

    private void reviseValue(ValueReviser valueReviser, Identifier identifier) {
        valueReviser.reviseValue(identifier, createValue(slider));
    }

    @Override
    public Pane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.add(slider, 0, 0);
        return gridPane;
    }

    public void updateWidgetValue(Value value) {
        slider.setValue(extractSliderValue(value));
    }

    protected double extractSliderValue(Value value) {
        return value.toDouble();
    }

    protected Value createValue(Slider slider) {
        return new DecimalValue(new BigDecimal(slider.getValue()));
    }
}
