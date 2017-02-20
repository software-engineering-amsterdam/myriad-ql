package org.uva.taxfree.model;

import javax.swing.*;

public class BooleanQuestion extends NamedNode {
    private Boolean mValue;

    public BooleanQuestion(String description, String id) {
        super(description, id);
        mValue = new Boolean(false);
    }

    @Override
    protected void fillPanel(JPanel parentPanel) {
        parentPanel.add(new JCheckBox("", mValue.booleanValue()));
    }

    public boolean isVisible() {
        return mValue.booleanValue();
    }

    @Override
    public String getType() {
        return "boolean";
    }

}
