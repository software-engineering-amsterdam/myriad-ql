package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.IntegerType;
import ql.astnodes.types.StringType;
import ql.astnodes.types.Type;
import ql.gui.formenvironment.values.IntegerValue;
import ql.gui.formenvironment.values.Value;
import qls.astnodes.styles.Style;
import qls.astnodes.visitors.StyleSheetVisitor;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by LGGX on 04-Mar-17.
 */
public class QLSSpinBox extends QLSWidget {

    private static final int MIN = -100;
    private static final int MAX = 100;
    private static final int STEP = 1;

    private JSpinner spinbox;

    public QLSSpinBox() {

    }

    public QLSSpinBox(String label, LineNumber lineNumber) {
        super(lineNumber);
        this.componentLabel.setText(label);

        SpinnerModel spinnerModel = new SpinnerNumberModel(0, MIN, MAX, STEP);
        this.spinbox = new JSpinner(spinnerModel);

        this.component.add(this.componentLabel);
        this.component.add(this.spinbox);

    }

    @Override
    public void applyStyle(Style style) {
        style.getInheritedStyle(this.getDefaultStyle());

        Font font = new Font(
                style.getFont(this.getDefaultFont().getValue()), 0,
                style.getFontSize(Integer.parseInt(this.getDefaultFontSize().getValue()))
        );
        this.componentLabel.setFont(font);

        Color color = style.getColor(Integer.parseInt(this.getDefaultColor().getValue()));
        this.componentLabel.setForeground(color);

        JComponent editor = this.spinbox.getEditor();
        JFormattedTextField ftf = ((JSpinner.DefaultEditor) editor).getTextField();
        ftf.setColumns(Integer.parseInt(this.getDefaultWidth().getValue()) / 2);

    }

    @Override
    public void addListener(EventListener listener) {

        this.spinbox.addChangeListener(
                e -> {

                }
        );
    }

    @Override
    public IntegerValue getValue() {
        return new IntegerValue((int) this.spinbox.getValue());
    }

    @Override
    public void setValue(Value nvalue) {
        IntegerValue value = (IntegerValue) nvalue;
        this.spinbox.setValue(value.getValue());
    }

    @Override
    public void setReadOnly(boolean isReadonly) {
        this.spinbox.setEnabled(false);
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) this.spinbox.getEditor();
        editor.getTextField().setEnabled( true );
        editor.getTextField().setEditable( false );
    }

    public java.util.List<Type> getSupportedQuestionTypes() {
        java.util.List<Type> supportedTypes = new ArrayList<>(
                Arrays.asList(
                        new IntegerType(),
                        new StringType()
                )
        );
        return supportedTypes;
    }

    @Override
    public void setLabel(String label) {
        this.componentLabel.setText(label);
    }

    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
