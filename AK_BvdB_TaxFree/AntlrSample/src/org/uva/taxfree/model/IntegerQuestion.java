package org.uva.taxfree.model;

import javax.swing.*;

public class IntegerQuestion extends NamedNode {
    private JFormattedTextField mTextField;

    public IntegerQuestion(String description, String id) {
        super(description, id);
        mTextField = new JFormattedTextField();
        // add numbers only
        mTextField.setValue(0);
    }

    @Override
    protected void fillPanel(JPanel parent) {
        parent.add(mTextField);
    }

    @Override
    public String resolve() {
        return mTextField.getText();
    }

}
