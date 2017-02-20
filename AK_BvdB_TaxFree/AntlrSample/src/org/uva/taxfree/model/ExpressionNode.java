package org.uva.taxfree.model;

import javax.swing.*;

public class ExpressionNode extends NamedNode {
    public ExpressionNode(String description, String id) {
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

    @Override
    public String getType() {
        return null;
    }
}
