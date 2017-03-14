package org.uva.taxfree.gui.widgets;

public class IntegerWidget  extends TextFieldWidget{
    public IntegerWidget(String label, String id) {
        super(label, id);
    }

    @Override
    public Object getFormatObject() {
        return 0;
    }


}
