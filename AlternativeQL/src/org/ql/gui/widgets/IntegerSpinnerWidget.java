package org.ql.gui.widgets;

import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.IntegerValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;

public class IntegerSpinnerWidget extends SpinnerWidget<Integer> {
    private final IntegerSpinnerValueFactory spinnerValueFactory;

    public IntegerSpinnerWidget(ValueReviser mediator, Question question) {
        super(mediator, question);
        spinnerValueFactory = new IntegerSpinnerValueFactory(
            Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1
        );
    }

    @Override
    protected Value createValue(Integer value) {
        return new IntegerValue(value);
    }

    @Override
    protected IntegerSpinnerValueFactory createSpinnerValueFactory() {
        return spinnerValueFactory;
    }

    @Override
    public void updateValue(Value value) {
        spinnerValueFactory.setValue(value.toInteger());
    }
}
