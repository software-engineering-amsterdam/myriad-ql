package org.uva.taxfree.model.node.widgets;

public class StringWidget extends TextFieldWidget {
    public StringWidget(String label) {
        super(label);
    }

    @Override
    public Object getFormatObject() {
        return "";
    }
}
