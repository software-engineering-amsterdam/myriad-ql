package org.uva.taxfree.model;

import javax.swing.*;

public class BoolQuestion extends QuestionNode {
    private boolean mValue;
    public BoolQuestion(String description, String id, Node parent){
        super(description, id, parent);
    }
    @Override
    protected void fillPanel(JPanel panel) {
        panel.add(new JCheckBox("", mValue));
    }
}
