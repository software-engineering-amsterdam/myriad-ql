package org.uva.taxfree.model.node.declarations;

import javax.swing.*;
import java.awt.*;

public class StringQuestion extends NamedNode {

    private final JTextField mTextField;

    public StringQuestion(String description, String id) {
        super(description, id);
        mTextField = new JTextField("");
    }

    @Override
    public void fillPanel(JPanel parentPanel) {
        mTextField.setPreferredSize(new Dimension(100, 25));
        parentPanel.add(mTextField);
    }

    @Override
    public String resolveValue() {
        return mTextField.getText();
    }

    @Override
    public void setVisible(boolean isVisible) {
        mTextField.setVisible(isVisible);
        super.setVisible(isVisible);
    }

    @Override
    public void printValue() {
        System.out.println("Value of string: " + mTextField.getText());
    }
}
