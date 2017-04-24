package org.ql.gui.widgets;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import org.ql.ast.identifier.Identifier;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.DecimalValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.FormPane;
import org.ql.gui.ValueReviser;

import java.math.BigDecimal;

public class SliderWidget extends GUIWidget {
    private final Slider slider;
    private final Label label;

    public SliderWidget(ValueReviser valueReviser, Question question) {
        label = new Label(question.getLabel().toString());
        slider = new Slider(0, 100, 50);
        slider.setOnMouseClicked(event -> reviseValue(valueReviser, question.getId()));
        slider.setOnKeyReleased(event -> reviseValue(valueReviser, question.getId()));
    }

    private void reviseValue(ValueReviser valueReviser, Identifier identifier) {
        valueReviser.reviseValue(identifier, createValue(slider));
    }

    @Override
    public Pane createGridPane() {
        FormPane gridPane = new FormPane();
        gridPane.add(label, 0, 0);
        gridPane.add(slider, 1, 0);
        return gridPane;
    }

    public void updateWidgetValue(Value value) {
        slider.setValue(extractSliderValue(value));
    }

    private double extractSliderValue(Value value) {
        return value.toDouble();
    }

    private Value createValue(Slider slider) {
        return new DecimalValue(new BigDecimal(slider.getValue()));
    }
}
