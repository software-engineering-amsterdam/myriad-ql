package org.ql.gui.widgets;

import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.DecimalValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;

import java.math.BigDecimal;

public class DecimalSpinnerWidget extends SpinnerWidget<Double> {

    private final DoubleSpinnerValueFactory spinnerValueFactory;

    public DecimalSpinnerWidget(ValueReviser mediator, Question question) {
        super(mediator, question);
        spinnerValueFactory = new DoubleSpinnerValueFactory(Double.MIN_VALUE, Double.MAX_VALUE, 0, 0.01);
    }

    @Override
    protected Value createValue(Double value) {
        return new DecimalValue(new BigDecimal(value));
    }

    @Override
    protected DoubleSpinnerValueFactory createSpinnerValueFactory() {
        return spinnerValueFactory;
    }

    @Override
    public void updateValue(Value value) {
        spinnerValueFactory.setValue(value.toDouble());
    }

}
