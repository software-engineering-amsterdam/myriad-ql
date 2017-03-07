package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.types.StringType;
import org.uva.taxfree.model.types.Type;

public class StringQuestion extends TextFieldQuestion {
    public StringQuestion(String description, String id) {
        super(description, id);
    }

    @Override
    protected Object getFormatObject() {
        return new String();
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
