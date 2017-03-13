package org.lemonade.gui.elements.elements;

import java.time.LocalDate;

import org.lemonade.gui.elements.GuiElement;
import org.lemonade.gui.elements.values.GuiDateValue;
import org.lemonade.gui.elements.values.GuiUndefinedValue;
import org.lemonade.gui.elements.values.GuiValue;

import javafx.scene.control.DatePicker;

public class GuiDateElement implements GuiElement {

    private GuiValue<?> value;
    private DatePicker picker;

    public GuiDateElement() {
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
