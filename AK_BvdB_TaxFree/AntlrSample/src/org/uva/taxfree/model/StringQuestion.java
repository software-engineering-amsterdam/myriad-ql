package org.uva.taxfree.model;

import javax.swing.*;

public class StringQuestion extends NamedNode {
    private String mValue;

    public StringQuestion(String description, String id) {
        super(description, id);
        mValue = new String("Enter your text here!");
    }

    @Override
    public void fillPanel(JPanel parentPanel) {
        parentPanel.add(new JTextField(mValue));
    }

    @Override
    public String getType() {
        return "string";
    }
}
