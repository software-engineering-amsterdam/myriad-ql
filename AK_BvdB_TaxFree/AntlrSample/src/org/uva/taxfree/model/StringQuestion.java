package org.uva.taxfree.model;

import javax.swing.*;
import java.awt.*;

public class StringQuestion extends NamedNode {

    private JTextField mTextField;
    public StringQuestion(String description, String id) {
        super(description, id);
        mTextField = new JTextField("");
    }

    @Override
    public void fillPanel(JPanel parentPanel) {
        mTextField.setPreferredSize(new Dimension(100,25));
        setVisible(true);
        parentPanel.add(mTextField);
    }

    public void setVisible(boolean isVisible){
        mTextField.setVisible(isVisible);
    }

    @Override
    public String getType() {
        return "string";
    }

    @Override
    public void printValue(){
        System.out.println("Value of string: " + mTextField.getText());
    }
}
