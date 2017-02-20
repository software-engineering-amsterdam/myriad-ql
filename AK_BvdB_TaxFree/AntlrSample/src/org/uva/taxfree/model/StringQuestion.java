package org.uva.taxfree.model;

import javax.swing.*;
import java.awt.*;

public class StringQuestion extends NamedNode {
    private String mValue;

    public StringQuestion(String label, String id) {
        super(label, id);
        mValue = new String("");
    }

    @Override
    public void fillPanel(JPanel parentPanel) {
        JTextField f = new JTextField(mValue);
        f.setPreferredSize(new Dimension(100,25));
        parentPanel.add(f);
    }

    @Override
    public String getType() {
        return "string";
    }
}
