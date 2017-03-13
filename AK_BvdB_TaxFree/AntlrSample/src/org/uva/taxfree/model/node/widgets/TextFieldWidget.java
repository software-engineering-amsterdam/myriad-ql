package org.uva.taxfree.model.node.widgets;

import org.uva.taxfree.gui.FormListener;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public abstract class TextFieldWidget extends Widget {
    private final JFormattedTextField mTextField;

    public TextFieldWidget(String label, String id) {
        super(label, id);
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

    @Override
    public void callOnUpdate(FormListener listener) {
        mTextField.addPropertyChangeListener(evt -> listener.updateForm());
    }
}
