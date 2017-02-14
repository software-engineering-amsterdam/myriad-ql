package org.uva.taxfree.model;

import javax.swing.*;

public abstract class OperatorNode extends NamedNode {
    public OperatorNode(String description, String id) {
        super(description, id);
    }

    private String evaluate() {
        return "1 + 1 equals 2";
    }

    @Override
    protected void fillPanel(JPanel parent) {
        JTextField field = new JTextField(evaluate());
        field.setEditable(false);
        parent.add(field);
    }
}
