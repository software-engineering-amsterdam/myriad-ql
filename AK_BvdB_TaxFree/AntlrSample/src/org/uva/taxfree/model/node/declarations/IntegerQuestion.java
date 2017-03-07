package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.types.IntegerType;
import org.uva.taxfree.model.types.Type;

public class IntegerQuestion extends TextFieldQuestion {
    public IntegerQuestion(String description, String id) {
        super(description, id);
    }

    @Override
    protected Object getFormatObject() {
        return new Integer(0);
    }

    @Override
    public Type getType() {
        return new IntegerType();
    }
}
