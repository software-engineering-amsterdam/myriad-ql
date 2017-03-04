package org.uva.taxfree.model.node.declarations;

import java.util.Date;

public class DateQuestion extends TextFieldQuestion {
    public DateQuestion(String description, String id) {
        super(description, id);
    }

    @Override
    protected Object getFormatObject() {
        return new Date();
    }
}