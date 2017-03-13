package org.lemonade.gui.values;

import java.time.LocalDate;

public class GuiDateValue extends GuiValue<LocalDate> {

    private LocalDate value;

    public GuiDateValue(LocalDate value) {
        this.value = value;
    }

    @Override
    public LocalDate getValue() {
        return value;
    }

    @Override
    public void update(LocalDate value) {
        this.value = value;
    }

}
