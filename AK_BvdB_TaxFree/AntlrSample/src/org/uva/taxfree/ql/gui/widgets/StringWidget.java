package org.uva.taxfree.ql.gui.widgets;

import org.uva.taxfree.ql.model.values.StringValue;
import org.uva.taxfree.ql.model.values.Value;

public class StringWidget extends TextFieldWidget {
    public StringWidget(String label, String id) {
        super(label, id);
    }

    @Override
    public Object getFormatObject() {
        return "";
    }

    @Override
    public Value resolveValue() {
        return new StringValue(currentValue());
    }
}
