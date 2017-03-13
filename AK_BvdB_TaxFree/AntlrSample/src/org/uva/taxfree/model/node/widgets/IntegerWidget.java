package org.uva.taxfree.model.node.widgets;

public class IntegerWidget  extends TextFieldWidget{
    public IntegerWidget(String label, String id) {
        super(label, id);
    }

    @Override
    public Object getFormatObject() {
        return 0;
    }


}
