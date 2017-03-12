package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.BooleanType;
import ql.astnodes.types.StringType;
import ql.astnodes.types.Type;
import ql.gui.formenvironment.values.BooleanValue;
import ql.gui.formenvironment.values.Value;
import qls.astnodes.styles.Style;
import qls.astnodes.visitors.StyleSheetVisitor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LGGX on 04-Mar-17.
 */
public class QLSCheckBox extends QLSWidget{

    private JCheckBox checkBox;

    public QLSCheckBox() {

    }

    public QLSCheckBox(String label, LineNumber lineNumber) {
        super(lineNumber);
        this.componentLabel.setText(label);
        this.checkBox = new JCheckBox(label);
        this.checkBox.setHorizontalTextPosition(SwingConstants.LEFT);
        this.component.add(this.checkBox);
    }

    @Override
    public void setLabel(String label) {
        this.checkBox.setText(label);
    }

    @Override
    public void applyStyle(Style style) {
        style.AddDefaultInheritedStyles(this.getDefaultStyle());

        java.awt.Font font = new java.awt.Font(
                style.getFont(this.getDefaultFont().getValue()), 0,
                style.getFontSize(Integer.parseInt(this.getDefaultFontSize().getValue()))
        );
        this.checkBox.setFont(font);

        Color color = style.getColor(Integer.parseInt(this.getDefaultColor().getValue()));
        this.checkBox.setForeground(color);
    }

    @Override
    public BooleanValue getValue() {
        return new BooleanValue(this.checkBox.isSelected());
    }

    @Override
    public void setValue(Value nvalue) {
        BooleanValue value = (BooleanValue) nvalue;
        this.checkBox.setSelected(value.getValue());
    }

    public List<Type> getSupportedQuestionTypes() {
        List<Type> supportedTypes = new ArrayList<>();
        supportedTypes.add(new StringType());
        supportedTypes.add(new BooleanType());
        return supportedTypes;
    }

    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
