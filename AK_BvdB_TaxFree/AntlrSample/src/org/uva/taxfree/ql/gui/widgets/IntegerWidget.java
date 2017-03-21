package org.uva.taxfree.ql.gui.widgets;

import org.uva.taxfree.ql.model.values.IntValue;
import org.uva.taxfree.ql.model.values.Value;

public class IntegerWidget extends TextFieldWidget {
    public IntegerWidget(String label, String id) {
        super(label, id);
    }

    @Override
    public Object getFormatObject() {
        return 0;
    }

    @Override
    public Value resolveValue() {
        return new IntValue(Integer.valueOf(currentValue()));
    }
}
