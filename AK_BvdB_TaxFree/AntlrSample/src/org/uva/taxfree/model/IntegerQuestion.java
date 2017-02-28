package org.uva.taxfree.model;

import javax.swing.*;
import java.awt.*;

public class IntegerQuestion extends NamedNode {
    private JFormattedTextField mTextField;

    public IntegerQuestion(String description, String id) {
        super(description, id);
        mTextField = new JFormattedTextField();
        mTextField.setValue(0);
        mTextField.setPreferredSize(new Dimension(100, 25));
    }

    @Override
    protected void fillPanel(JPanel parent) {
        parent.add(mTextField);
    }

    @Override
    public String resolve() {
        return mTextField.getValue().toString();
    }

}
