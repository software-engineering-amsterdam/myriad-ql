package org.uva.taxfree.ql.gui.widgets;

public class StringWidget extends TextFieldWidget {
    public StringWidget(String label, String id) {
        super(label, id);
    }

    @Override
    public Object getFormatObject() {
        return "";
    }
}
