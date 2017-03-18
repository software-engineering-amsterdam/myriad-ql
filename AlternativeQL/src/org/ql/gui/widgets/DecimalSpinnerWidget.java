package org.ql.gui.widgets;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.ql.ast.statement.ComputableQuestion;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.DecimalValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;

import java.math.BigDecimal;

public class DecimalSpinnerWidget extends SpinnerWidget<Double> {

    public DecimalSpinnerWidget(ValueReviser mediator, Question question) {
        super(mediator, question, new SpinnerValueFactory.DoubleSpinnerValueFactory(
            Double.MIN_VALUE, Double.MAX_VALUE, 0, 0.01
        ));
    }

    @Override
    protected Double extractSpinnerValue(Value value) {
        return value.toDouble();
    }

    @Override
    protected Value createValue(Spinner<Double> spinner) {
        return new DecimalValue(new BigDecimal(spinner.getValue()));
    }
}
