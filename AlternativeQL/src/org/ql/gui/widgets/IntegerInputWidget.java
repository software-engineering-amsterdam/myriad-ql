package org.ql.gui.widgets;

import javafx.scene.control.TextFormatter;

public class IntegerInputWidget extends InputWidget {

    public IntegerInputWidget(String label) {
        super(label, createTextFormatter());
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
