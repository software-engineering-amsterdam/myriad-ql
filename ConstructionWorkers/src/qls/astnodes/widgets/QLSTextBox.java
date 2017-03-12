package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.StringType;
import ql.astnodes.types.Type;
import ql.gui.formenvironment.values.StringValue;
import ql.gui.formenvironment.values.Value;
import qls.astnodes.styles.Style;
import qls.astnodes.styles.Width;
import qls.astnodes.visitors.StyleSheetVisitor;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by LGGX on 04-Mar-17.
 */
public class QLSTextBox extends QLSWidget {

    private final static int WIDTH = 7;

    private JTextField input;

    public QLSTextBox() {

    }

    public QLSTextBox(String label, LineNumber lineNumber) {
        super(lineNumber);
        this.input = new JTextField();
        this.componentLabel.setText(label);
        this.component.add(componentLabel);
        this.component.add(input);

    }

    @Override
    public void setLabel(String label) {
        this.componentLabel.setText(label);
    }

    @Override
    public void applyStyle(Style style) {
        style.AddDefaultInheritedStyles(this.getDefaultStyle());

        Font font = new Font(
                style.getFont(this.getDefaultFont().getValue()), 0,
                style.getFontSize(Integer.parseInt(this.getDefaultFontSize().getValue()))
        );
        this.componentLabel.setFont(font);

        Color color = style.getColor(Integer.parseInt(this.getDefaultColor().getValue()));
        this.componentLabel.setForeground(color);
        this.input.setColumns(Integer.parseInt(this.getDefaultWidth().getValue()));
    }

    @Override
    public StringValue getValue() {
        return new StringValue(this.input.getText());
    }

    @Override
    public void setValue(Value nvalue) {
        StringValue value = (StringValue) nvalue;
        this.input.setText(value.getValue());
    }

    @Override
    public Width getDefaultWidth() {
        return new Width(WIDTH, getLineNumber());
    }

    public List<Type> getSupportedQuestionTypes() {
        List<Type> supportedTypes = new ArrayList<>();
        supportedTypes.add(new StringType());
        return supportedTypes;
    }

    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
