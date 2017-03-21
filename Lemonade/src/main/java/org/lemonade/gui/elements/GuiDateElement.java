package org.lemonade.gui.elements;

import javafx.scene.control.DatePicker;

import org.lemonade.gui.values.GuiDateValue;
import org.lemonade.gui.values.GuiUndefinedValue;
import org.lemonade.gui.values.GuiValue;

import java.time.LocalDate;

public class GuiDateElement extends GuiElement {

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
            value = new GuiUndefinedValue();
        else if (value.isDefined())
            ((GuiDateValue) value).update(pickerValue);
        else
            value = new GuiDateValue(pickerValue);
    }

    @Override
    public void clear() {
        value = new GuiUndefinedValue();
        picker.setValue(null);
    }

    @Override
    public DatePicker getWidget() {
        return picker;
    }

}
