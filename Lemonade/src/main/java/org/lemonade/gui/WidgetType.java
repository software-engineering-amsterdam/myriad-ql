package org.lemonade.gui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public enum WidgetType {
    CHECKBOX,
    TEXTFIELD,
    IMMUTABLE;

    public static Control getWidgetForType(WidgetType type) {
        if (type == CHECKBOX) {
            return new CheckBox();
        } else if (type == TEXTFIELD) {
            return new TextField();
        } else if (type == IMMUTABLE) {
            return new Label();
        } else {
            throw new IllegalArgumentException("Unknown widget type!");
        }
    }
}
