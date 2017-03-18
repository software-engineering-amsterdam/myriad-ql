package org.ql.gui.widgets;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import org.ql.ast.statement.ComputableQuestion;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.IntegerValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;

public class IntegerSpinnerWidget extends SpinnerWidget<Integer> {

    public IntegerSpinnerWidget(ValueReviser mediator, Question question) {
        super(mediator, question, new IntegerSpinnerValueFactory(
            Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1
        ));
    }

    @Override
    protected Integer extractSpinnerValue(Value value) {
        return value.toInteger();
    }

    @Override
    protected Value createValue(Spinner<Integer> spinner) {
        return new IntegerValue(spinner.getValue());
    }
}
