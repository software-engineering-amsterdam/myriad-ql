package org.lemonade.gui.elements.values;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;

public class GuiDateValue extends GuiValue<LocalDate> {

    private LocalDate value;
    private DatePicker picker;

    public GuiDateValue() {
        picker = new DatePicker();
        picker.setOnAction(e -> update());
    }

    @Override
    public LocalDate getValue() {
        return value;
    }

    @Override
    public void setValue(final LocalDate value) {
        this.value = value;
        System.err.println("new value = " + value);
    }

    @Override
    public void update() {
        setValue(picker.getValue());
    }

    @Override
    public DatePicker getWidget() {
        return picker;
    }
}
