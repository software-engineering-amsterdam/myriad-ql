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
    public void fillPanel(GuiComponent parentPanel) {
        parentPanel.add(mTextField);
    }

    protected String currentValue() {
        return String.valueOf(mTextField.getValue());
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
    protected void applyStyle(GuiComponent panel, QlsStyle qlsStyle) {
        super.applyStyle(panel, qlsStyle);
        qlsStyle.applyStyle(new StringType(), panel);
    }
}
