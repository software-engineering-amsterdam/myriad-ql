package org.uva.taxfree.ql.gui.widgets;

import java.util.Date;

public class DateWidget extends TextFieldWidget {
    public DateWidget(String label, String id) {
        super(label, id);
    }

    @Override
    public Object getFormatObject() {
        return new Date();
    }
}
