package org.uva.taxfree.model.node.declarations;

import javax.swing.*;
import java.awt.*;

public abstract class TextFieldQuestion extends NamedNode {

    private final JFormattedTextField mTextField;

    public TextFieldQuestion(String description, String id) {
        super(description, id);
        mTextField = createTextField();
    }

    private JFormattedTextField createTextField(){
        JFormattedTextField textField = new JFormattedTextField(getFormatObject());
        textField.setPreferredSize(new Dimension(100, 25));
        return textField;
    }

    // abstract to force instantiation of a correct format
    protected abstract Object getFormatObject();

    @Override
    public void fillPanel(JPanel parentPanel) {
        parentPanel.add(mTextField);
    }

    @Override
    public void printValue() {
        System.out.println("Value of string: " + resolveValue());
    }

    @Override
    public String resolveValue() {
        return mTextField.getValue().toString();
    }

}
