package org.lemonade.gui.elements;

import javafx.scene.control.DatePicker;

import org.lemonade.gui.values.GuiDateValue;
import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;

import java.time.LocalDate;

public class GuiDateElement implements GuiElement {

    private GuiValue<?> value;
    private DatePicker picker;

    public GuiDateElement() {
        value = new GuiUndefinedValue();
        picker = new DatePicker();
        picker.setOnAction(e -> update());
    }

    @Override
    public GuiValue<?> getValue() {
        return value;
    }

    @Override
    public void update() {
        LocalDate pickerValue = picker.getValue();
        if (pickerValue == null)
            this.value = new GuiUndefinedValue();
        else if (this.value.isDefined())
            ((GuiDateValue) this.value).update(pickerValue);
        else
            this.value = new GuiDateValue(pickerValue);
    }

    @Override
    public DatePicker getWidget() {
        return picker;
    }

}
