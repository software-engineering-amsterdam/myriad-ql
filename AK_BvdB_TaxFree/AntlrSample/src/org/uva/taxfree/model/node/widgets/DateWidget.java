package org.uva.taxfree.model.node.widgets;

import java.util.Date;

public class DateWidget extends TextFieldWidget {
    public DateWidget(String label) {
        super(label);
    }

    @Override
    public Object getFormatObject() {
        return new Date();
    }
}
