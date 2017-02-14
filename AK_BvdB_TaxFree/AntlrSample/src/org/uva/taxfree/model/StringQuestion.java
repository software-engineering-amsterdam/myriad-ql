package org.uva.taxfree.model;

import javax.swing.*;

public class StringQuestion extends QuestionNode {
    private String mValue;
    public StringQuestion(String description, String id, Node parent){
        super(description, id, parent);
        mValue = new String("Enter your text here!");
    }

    @Override
    public void fillPanel(JPanel parent) {
        parent.add(new JTextField(mValue));
    }
}
