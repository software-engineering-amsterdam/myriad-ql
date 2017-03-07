package org.uva.taxfree.model.node.declarations;

import org.uva.taxfree.model.types.DateType;
import org.uva.taxfree.model.types.Type;

import java.util.Date;

public class DateQuestion extends TextFieldQuestion {
    public DateQuestion(String description, String id) {
        super(description, id);
    }

    @Override
    protected Object getFormatObject() {
        return new Date();
    }

    @Override
    public Type getType() {
        return new DateType();
    }
}