package org.ql.gui.widgets;

import javafx.scene.control.TextFormatter;
import javafx.util.converter.BigDecimalStringConverter;
import org.ql.ast.statement.Question;
import org.ql.evaluator.value.DecimalValue;
import org.ql.evaluator.value.UnknownValue;
import org.ql.evaluator.value.Value;
import org.ql.gui.mediator.GUIMediator;

import java.math.BigDecimal;

public class DecimalInputWidget extends InputWidget {

    public DecimalInputWidget(GUIMediator mediator, Question question) {
        super(mediator, question, createTextFormatter());
    }

    private static TextFormatter<BigDecimal> createTextFormatter() {
        return new TextFormatter<>(new BigDecimalStringConverter(), new BigDecimal("0.0"), change -> {
            String text = change.getText();

            if (text.matches("[0-9]+(\\.[0-9]+)?")) {
                return change;
            }

            return change;
        });
    }

    @Override
    protected Value value(String textFieldText) {
        if (textFieldText.isEmpty()) {
            return new UnknownValue();
        }

        return new DecimalValue(new BigDecimal(textFieldText));
    }
}
