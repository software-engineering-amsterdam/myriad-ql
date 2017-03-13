package org.uva.taxfree.model.node.widgets;

public class IntegerWidget  extends TextFieldWidget{
    public IntegerWidget(String label) {
        super(label);
    }

    @Override
    public Object getFormatObject() {
        return 0;
    }
}
