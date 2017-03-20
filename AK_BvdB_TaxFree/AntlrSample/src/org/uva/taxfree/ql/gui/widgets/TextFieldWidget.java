package org.uva.taxfree.ql.gui.widgets;

import org.uva.taxfree.ql.gui.FormListener;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.types.StringType;
import org.uva.taxfree.qls.QlsStyle;

import javax.swing.*;
import java.awt.*;

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
        mTextField.addPropertyChangeListener(unusedEvent -> listener.updateForm());
    }

    @Override
    public void updateValues(SymbolTable symbolTable) {
        writeToTable(symbolTable);
    }

    @Override
    protected void applyStyle(JPanel panel, JLabel label, QlsStyle qlsStyle) {
        super.applyStyle(panel, label, qlsStyle);
        qlsStyle.applyStyle(new StringType(), panel);
        qlsStyle.applyStyle(new StringType(), label);
        qlsStyle.applyStyle(new StringType(), mTextField);
    }
}
