package org.ql.gui.widgets;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;

public class DecimalInputWidget extends InputWidget<BigDecimal> {

    DecimalInputWidget(String label) {
        super(label, createFormatter());
    }

    private static TextFormatter<BigDecimal> createFormatter() {
        return new TextFormatter<>(new BigDecimalStringConverter(), new BigDecimal("0.0"), change -> {
            String text = change.getText();

            if (text.matches("[0-9]+(\\.[0-9]+)?")) {
                return change;
            }

            return null;
        });
    }

    @Override
    protected BigDecimal extractValue(TextField textField) {
        return new BigDecimal(textField.getText());
    }
}
