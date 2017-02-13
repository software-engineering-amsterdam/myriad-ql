package org.uva.taxfree.model;

import javax.swing.*;

public class BooleanValue extends Value {
    @Override
    protected void fillPanel(JPanel panel) {
        panel.add(new JCheckBox());
    }
}
