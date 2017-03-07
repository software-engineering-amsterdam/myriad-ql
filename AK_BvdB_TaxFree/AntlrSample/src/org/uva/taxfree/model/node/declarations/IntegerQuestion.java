package org.uva.taxfree.model.node.declarations;

import java.text.NumberFormat;

public class IntegerQuestion extends TextFieldQuestion {
    public IntegerQuestion(String description, String id) {
        super(description, id);
    }

    @Override
    protected Object getFormatObject() {
        return new Integer(0);
    }
}
