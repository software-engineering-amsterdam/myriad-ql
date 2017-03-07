package org.lemonade.gui.elements;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;

public class GuiDateValue extends GuiValue<LocalDate> {

    private LocalDate value;
    private DatePicker picker;

    @Override
    public LocalDate getValue() {
        return null;
    }

    @Override
    public void setValue(final LocalDate value) {
        this.value = value;
    }

    @Override
    public void update() {
        setValue(picker.getValue());
    }
}
