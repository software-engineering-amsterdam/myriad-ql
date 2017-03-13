package org.uva.taxfree.model.node.widgets;

import javax.swing.*;
import java.awt.*;

public abstract class TextFieldWidget extends Widget {
    private final JFormattedTextField mTextField;

    public TextFieldWidget(String label) {
        super(label);
        mTextField = createTextField();
    }

    private JFormattedTextField createTextField() {
        JFormattedTextField textField = new JFormattedTextField(getFormatObject());
        textField.setPreferredSize(new Dimension(100, 25));
        return textField;
    }

    public abstract Object getFormatObject();

    @Override
    public void fillPanel(JPanel parentPanel) {
        parentPanel.add(mTextField);
    }

    @Override
    public String resolveValue() {
        return mTextField.getValue().toString();
    }
}
