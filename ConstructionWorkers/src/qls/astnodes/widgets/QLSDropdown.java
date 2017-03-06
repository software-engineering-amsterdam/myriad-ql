package qls.astnodes.widgets;

import ql.astnodes.LineNumber;
import ql.astnodes.types.BooleanType;
import ql.astnodes.types.Type;
import ql.gui.formenvironment.values.BooleanValue;
import ql.gui.formenvironment.values.Value;
import qls.astnodes.styles.Style;
import qls.astnodes.visitors.StyleSheetVisitor;
import qls.astnodes.widgets.widgettypes.DropdownType;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by LGGX on 04-Mar-17.
 */
public class QLSDropdown extends QLSWidget {

    private static final String DEFAULT_YES_TEXT = "Yes";
    private static final String DEFAULT_NO_TEXT = "No";

    private String yesLabel;
    private String noLabel;

    private JComboBox comboBox;

    public QLSDropdown(){
    }

    public QLSDropdown(String _label, String _yes, String _no, LineNumber lineNumber) {
        super(lineNumber);

        this.yesLabel = _yes;
        this.noLabel = _no;
        this.componentLabel.setText(_label);

        String[] valueArray = {this.yesLabel, this.noLabel};
        this.comboBox = new JComboBox(valueArray);

        this.component.add(this.componentLabel);
        this.component.add(this.comboBox);

        this.type = new DropdownType();
    }

    @Override
    public void applyStyle(Style _style) {
        _style.getInheritedStyle(this.getDefaultStyle());

        Font font = new Font(
                _style.getFont(this.getDefaultFont().getValue()), 0,
                _style.getFontSize(Integer.parseInt(this.getDefaultFontSize().getValue()))
        );
        this.componentLabel.setFont(font);

        Color color = _style.getColor(Integer.parseInt(this.getDefaultColor().getValue()));
        this.componentLabel.setForeground(color);

        this.comboBox.setPreferredSize(
                new Dimension(
                        Integer.parseInt(this.getDefaultWidth().getValue()),
                        (int) this.comboBox.getPreferredSize().getHeight()
                )
        );
    }

    @Override
    public void addListener(EventListener _listener) {
        this.comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    @Override
    public BooleanValue getValue() {
        String selectedValue = (String) this.comboBox.getSelectedItem();
        if (selectedValue.equals(this.yesLabel)) {
            return  new BooleanValue(true);
        }
        return new BooleanValue(false);
    }

    @Override
    public void setValue(Value nvalue) {
        BooleanValue value = (BooleanValue) nvalue;
        if (value.getValue().equals(true)) {
            this.comboBox.setSelectedItem(this.yesLabel);
        } else {
            this.comboBox.setSelectedItem(this.noLabel);
        }
    }

    @Override
    public void setReadOnly(boolean _isReadonly) {
        this.comboBox.setEnabled(false);
    }

    public java.util.List<Type> getSupportedQuestionTypes() {
        java.util.List<Type> supportedTypes = new ArrayList<>(
                Arrays.asList(new BooleanType())
        );
        return supportedTypes;
    }

    public <T> T accept(StyleSheetVisitor<T> visitor) {
        return visitor.visit(this);
    }
}