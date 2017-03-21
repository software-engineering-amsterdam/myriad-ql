package org.uva.taxfree.ql.gui.widgets;

import org.uva.taxfree.ql.model.values.StringValue;
import org.uva.taxfree.ql.model.values.Value;

import java.util.Date;

public class DateWidget extends TextFieldWidget {
    public DateWidget(String label, String id) {
        super(label, id);
    }

    @Override
    public Object getFormatObject() {
        return new Date();
    }

    @Override
    public Value resolveValue() {
        return new StringValue(currentValue());
    }
}
