package org.uva.taxfree.model;

import javax.swing.*;

public class IntegerQuestion extends NumberNode {
    private Integer mValue;

    public IntegerQuestion(String description, String id, Integer defaultValue) {
        super(description, id);
        mValue = defaultValue;
    }

    public Number getValue() {
        return mValue.intValue();
    }

    @Override
    protected void fillPanel(JPanel parent) {
        JFormattedTextField field = new JFormattedTextField();
        field.setValue(mValue);
        parent.add(field);
    }

    @Override
    public String getType() {
        return "integer";
    }

    public int getInt() {
        return mValue.intValue();
    }
}
