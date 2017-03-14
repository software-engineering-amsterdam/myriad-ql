package org.uva.taxfree.model.node.widgets;

public class StringWidget extends TextFieldWidget {
    public StringWidget(String label, String id) {
        super(label, id);
    }

    @Override
    public Object getFormatObject() {
        return "";
    }
}
