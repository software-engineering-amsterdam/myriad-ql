package org.uva.taxfree.model;

import javax.swing.*;

public class StringValue extends Value {
    @Override
    public void fillPanel(JPanel parent) {
        parent.add(new JTextField());
    }
}
