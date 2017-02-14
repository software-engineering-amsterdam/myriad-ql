package org.uva.taxfree.model;

import javax.swing.*;

public class BooleanQuestion extends NamedNode {
    private Boolean mValue;

    public BooleanQuestion(String description, String id) {
        super(description, id);
    }

    @Override
    protected void fillPanel(JPanel parentPanel) {
        parentPanel.add(new JCheckBox("", mValue));
    }

    public boolean isVisible() {
        return mValue.booleanValue();
    }

    @Override
    public String getType() {
        return "boolean";
    }

}
