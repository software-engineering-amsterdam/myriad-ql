package org.ql.gui.widgets;

import javafx.scene.control.TextFormatter;
import org.ql.ast.statement.ComputableQuestion;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.IntegerValue;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.ValueReviser;

public class IntegerInputWidget extends InputWidget {

    public IntegerInputWidget(ValueReviser valueReviser, Question question) {
        super(valueReviser, question, createTextFormatter());
    }

    @Override
    protected Value value(String textFieldText) {
        if (textFieldText.isEmpty()) {
            return new UnknownValue();
        }

        return new IntegerValue(Integer.parseInt(textFieldText));
    }

    private static TextFormatter<Integer> createTextFormatter() {
        return new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        });
    }
}
